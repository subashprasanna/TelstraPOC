package com.cts.telstrapoc.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
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
import com.cts.telstrapoc.util.AppUtil
import kotlinx.android.synthetic.main.country_detail_fragment.*
import javax.inject.Inject


class CountryFragment : Fragment() {

    private lateinit var viewModel: CountryViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    /**
     *  This app has two ways to check internet connection
     *  1. Manually call function Util -> AppUtil -> isOnline() Ex: AppUtil.isOnline(context) then call viewModel.getCanadaInfo()
     *  (or)
     *  2. Use below broadcast receiver to detect internet connection
     *  (For easy testing purpose, i have implemented broadcast logic in this fragment)
     */
    private var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val isInternetNotConnected = intent.getBooleanExtra(
                ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
            if (isInternetNotConnected) {
                internetDisconnected()
            } else {
                internetConnected()
            }
        }
    }

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
            viewModel.getCanadaInfo()
            pull_to_refresh_container.isRefreshing = false
        }
    }

    private fun initializeViews() {
        /**
         * Show normal progressbar only once until canada api gives response
         * Once data comes from api pull to refresh handles progressbar
         */
        progressbar_initial.visibility = View.VISIBLE

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

    private fun setData(canadaDetail: List<CanadaAPIDetailInfo>) {
        pull_to_refresh_container.visibility = View.VISIBLE
        progressbar_initial.visibility = View.GONE
        recycler_view_country.adapter =
            CountryAdapter(canadaDetail)
    }

    override fun onStart() {
        super.onStart()
        // Register broadcast receiver for internet connection
        context?.registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        super.onStop()
        // Un-Register broadcast receiver for internet connection
        context?.unregisterReceiver(broadcastReceiver)
    }

    private fun internetDisconnected() {
        pull_to_refresh_container.visibility = View.GONE
        tv_no_internet.visibility = View.VISIBLE
        progressbar_initial.visibility = View.GONE
    }

    private fun internetConnected() {
        pull_to_refresh_container.visibility = View.GONE
        tv_no_internet.visibility = View.GONE
        progressbar_initial.visibility = View.VISIBLE
        viewModel.getCanadaInfo()
    }
}
