package com.danzucker.currency.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.danzucker.currency.databinding.FragmentDetailDashboardBinding
import com.danzucker.currency.presentation.detail.adapter.DetailViewPagerAdapter
import com.danzucker.currency.presentation.detail.historical.HistoricalFragment
import com.danzucker.currency.presentation.detail.popular.PopularCurrenciesEvent
import com.danzucker.currency.presentation.detail.popular.PopularCurrenciesViewModel
import com.danzucker.currency.presentation.detail.popular.PopularFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailDashboardFragment : Fragment() {

    private var currentBinding : FragmentDetailDashboardBinding? = null
    private val ui get() = currentBinding!!

    private val detailViewModel : DetailViewModel by viewModels()

    private val popularCurrenciesViewModel : PopularCurrenciesViewModel by viewModels()

    private lateinit var detailViewPagerAdapter: DetailViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        currentBinding = FragmentDetailDashboardBinding.inflate(inflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFragmentAndTitle ()

//        ui.history.setOnClickListener {
//            getPopularCurrencies ()
//            getPopularCurrenciesSubscriber()
//          //  getHistoricalCurrencyData ()
//            //getHistoricalCurrencySubscriber()
//        }

    }

    private fun loadFragmentAndTitle () {
        val fragmentManager = activity?.supportFragmentManager!!
        val fragmentList : ArrayList<Fragment> = arrayListOf(
            HistoricalFragment(),
            PopularFragment()
        )


        detailViewPagerAdapter = DetailViewPagerAdapter(
            fragmentList,
            fragmentManager,
            lifecycle
        )

        ui.fragmentSpendViewPager.adapter = detailViewPagerAdapter

        TabLayoutMediator(ui.fragmentSpendTabLayout, ui.fragmentSpendViewPager)
        { tab: TabLayout.Tab, i: Int ->

            when(i) {
                0 -> {
                    tab.text = "Currency History"
                }

                1 -> {
                    tab.text = "Popular Currencies"
                }
            }
        }.attach()

    }



    // For popular currencies
    private fun getPopularCurrencies () {
        popularCurrenciesViewModel.onTriggeredEvent(PopularCurrenciesEvent.GetPopularCurrencies(
            "EUR", "GBP, NGN, AMD"
        ))
    }

    private fun getPopularCurrenciesSubscriber() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            popularCurrenciesViewModel.popularCurrenciesViewState.collectLatest { state->
                if (state.error != ""){
                    Toast.makeText(requireContext(), "Error :${state.error}", Toast.LENGTH_LONG).show()
                    Log.i("IPPPO11", "DDD: ${state.error}")
                } else {
                    state.popularCurrencies?.let {
                        Toast.makeText(requireContext(), "Success :${state.popularCurrencies}", Toast.LENGTH_LONG).show()
                        Log.i("IPPPO", "DDD: ${state.popularCurrencies}")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentBinding = null
    }


}