package com.danzucker.currency.presentation.detail.historical

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.danzucker.currency.databinding.FragmentHistoricalBinding
import com.danzucker.currency.presentation.detail.DetailDashboardFragmentArgs
import com.danzucker.currency.presentation.detail.DetailViewEvent
import com.danzucker.currency.presentation.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HistoricalFragment : Fragment() {

    private var currenctBinding : FragmentHistoricalBinding? = null
    private val ui get() = currenctBinding!!

    private val detailViewModel : DetailViewModel by viewModels()

    private lateinit var base : String
    private lateinit var target : String
    private lateinit var currentDate : String
    private lateinit var lastThreeDaysDate : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        currenctBinding = FragmentHistoricalBinding.inflate(inflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        getHistoricalCurrencyData ()
        getHistoricalCurrencySubscriber()
    }

    private fun getHistoricalCurrencyData () {
        detailViewModel.onTriggeredEvent(
            DetailViewEvent.GetHistoricalCurrencyData(
                lastThreeDaysDate, currentDate, base, target
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

    override fun onDestroyView() {
        super.onDestroyView()
        currenctBinding = null
    }
}