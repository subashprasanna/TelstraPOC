@file:Suppress("DEPRECATION")

package com.cts.telstrapoc.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cts.telstrapoc.R
import com.cts.telstrapoc.di.DaggerDIComponent
import com.cts.telstrapoc.model.CanadaAPIDetailInfo
import com.cts.telstrapoc.ui.adapter.CountryAdapter
import com.cts.telstrapoc.util.AppUtil
import com.cts.telstrapoc.util.INITIAL_PAGE_TITLE
import com.cts.telstrapoc.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.country_detail_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@Suppress("DEPRECATION")
class CountryFragment : Fragment() {

    private lateinit var viewModel: CountryViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.country_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Set default actionbar title
        (activity as AppCompatActivity).supportActionBar?.title = INITIAL_PAGE_TITLE

        initializeViews()

        DaggerDIComponent.create().inject(this)

        viewModel = ViewModelProviders.of(this, factory).get(CountryViewModel::class.java)

        // Load canada country detail in UI
        viewModel.countryDetail.observe(viewLifecycleOwner, Observer { detail ->
            recycler_view_country.also {
                // Set custom actionbar title from canada's api response
                (activity as AppCompatActivity).supportActionBar?.title = detail.title

                setData(detail.rows)
            }
        })

        // Fetch and load UI on pull to refresh
        pull_to_refresh_container.setOnRefreshListener {
            loadAPIData()
            pull_to_refresh_container.isRefreshing = false
        }

        // check internet connection and load data
        loadAPIData()
    }

    private fun initializeViews() {
        recycler_view_country.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
        }

        pull_to_refresh_container.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(requireContext(),
                R.color.colorPrimary)
        )

        pull_to_refresh_container.setColorSchemeColors(Color.WHITE)
    }

    private fun loadAPIData() {
        progressbar_initial.visibility = View.VISIBLE
        tv_no_internet.visibility = View.GONE

        if (AppUtil.isOnline(context)) checkURLAndCallAPI() else internetDisconnected()
    }

    private fun setData(canadaDetail: List<CanadaAPIDetailInfo>) {
        pull_to_refresh_container.visibility = View.VISIBLE
        progressbar_initial.visibility = View.GONE
        recycler_view_country.adapter =
            CountryAdapter(canadaDetail)
    }

    private fun internetDisconnected() {
        pull_to_refresh_container.visibility = View.VISIBLE
        tv_no_internet.visibility = View.VISIBLE
        progressbar_initial.visibility = View.GONE
        setData(listOf<CanadaAPIDetailInfo>())
    }

    private fun internetConnected() {
        pull_to_refresh_container.visibility = View.GONE
        tv_no_internet.visibility = View.GONE
        viewModel.getCanadaInfo()
    }



    fun checkURLAndCallAPI() {
        // Check url is reachable from background IO thread
        GlobalScope.launch(Dispatchers.IO) {
            val check = AppUtil.checkURLReachable()
            // Update the ui from main thread
            withContext(Dispatchers.Main) {
                if (check) internetConnected() else internetDisconnected()
            }
        }
    }
}