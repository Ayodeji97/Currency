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
import com.danzucker.currency.presentation.detail.popular.PopularCurrenciesEvent
import com.danzucker.currency.presentation.detail.popular.PopularCurrenciesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailDashboardFragment : Fragment() {

    private var currentBinding : FragmentDetailDashboardBinding? = null
    private val ui get() = currentBinding!!

    private val detailViewModel : DetailViewModel by viewModels()

    private val popularCurrenciesViewModel : PopularCurrenciesViewModel by viewModels()

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

        ui.history.setOnClickListener {
            getPopularCurrencies ()
            getPopularCurrenciesSubscriber()
          //  getHistoricalCurrencyData ()
            //getHistoricalCurrencySubscriber()
        }

    }

    private fun getHistoricalCurrencyData () {
        detailViewModel.onTriggeredEvent(DetailViewEvent.GetHistoricalCurrencyData(
            "2022-11-16", "2022-11-18", "EUR", "NGN"
        ))
    }

    private fun getHistoricalCurrencySubscriber() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            detailViewModel.historicalDataViewState.collectLatest {state->
                if (state.error != "") {
                    Toast.makeText(requireContext(), "Error :${state.error}", Toast.LENGTH_LONG).show()
                    Log.i("IPPPO11", "DDD: ${state.error}")
                } else {
                    state.historicalData?.let {
                        Toast.makeText(requireContext(), "Success :${state.historicalData}", Toast.LENGTH_LONG).show()
                        Log.i("IPPPO", "DDD: ${state.historicalData}")
                    }
                }
            }
        }
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