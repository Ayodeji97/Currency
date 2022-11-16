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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailDashboardFragment : Fragment() {

    private var currentBinding : FragmentDetailDashboardBinding? = null
    private val ui get() = currentBinding!!

    private val detailViewModel : DetailViewModel by viewModels()

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
            getHistoricalCurrencyData ()
            getHistoricalCurrencySubscriber()
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
        currentBinding = null
    }


}