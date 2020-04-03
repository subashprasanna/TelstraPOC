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
import com.cts.telstrapoc.model.CanadaAPIDetailInfo
import com.cts.telstrapoc.ui.adapter.CountryAdapter
import com.cts.telstrapoc.util.INITIAL_PAGE_TITLE
import com.cts.telstrapoc.viewmodel.CountryViewModel
import com.cts.telstrapoc.R
import com.cts.telstrapoc.di.DaggerDIComponent
import kotlinx.android.synthetic.main.country_detail_fragment.*
import javax.inject.Inject


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

        // Fetch canada country detail
        viewModel.getCanadaInfo()

        // Load canada country detail in UI
        viewModel.countryDetail.observe(viewLifecycleOwner, Observer { detail ->
            recycler_view_canada.also {
                // Set custom actionbar title from canada's api response
                (activity as AppCompatActivity).supportActionBar?.title = detail.title

                setData(detail.rows)
            }
        })

        // Fetch and load UI on pull to refresh
        swipeContainer.setOnRefreshListener {
            viewModel.getCanadaInfo()
            swipeContainer.isRefreshing = false
        }
    }

    fun initializeViews() {
        /**
         * Show normal progressbar only once until canada api gives response
         * Once data comes from api pull to refresh handles progressbar
         */
        progressbar_initial.visibility = View.VISIBLE

        recycler_view_canada.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
        }

        swipeContainer.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(requireContext(),
                R.color.colorPrimary)
        )

        swipeContainer.setColorSchemeColors(Color.WHITE)
    }

    fun setData(canadaDetail: List<CanadaAPIDetailInfo>) {
        progressbar_initial.visibility = View.GONE
        recycler_view_canada.adapter =
            CountryAdapter(canadaDetail)
    }
}
