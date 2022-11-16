package com.danzucker.currency.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.danzucker.currency.R
import com.danzucker.currency.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var currentBinding: FragmentMainBinding? = null
    private val ui get() = currentBinding!!

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentBinding = FragmentMainBinding.inflate(inflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCurrencySymbolSubscriber()

        ui.sampleAction.setOnClickListener {
            test ()
            //findNavController().navigate(R.id.detailDashboardFragment)
//            getConvertedCurrency()
//            convertedCurrencySubscriber ()
        }

    }

    private fun getConvertedCurrency () {
        mainViewModel.onTriggeredEvent(MainViewEvent.GetConvertCurrencyData(
            "EUR", "NGN", "10"
        ))
    }

    private fun convertedCurrencySubscriber () {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainViewModel.convertCurrencyViewState.collectLatest { state->
                if (state.error != "") {
                    Toast.makeText(requireContext(), "Error :${state.error}", Toast.LENGTH_LONG).show()
                } else {
                    state.convertCurrency?.let {
                        Toast.makeText(requireContext(), "Success :${state.convertCurrency}", Toast.LENGTH_LONG).show()
                    }

                }

            }
        }
    }

    private fun test () {
        mainViewModel.onTriggeredEvent(MainViewEvent.GetCurrencySymbols)
    }

    private fun getCurrencySymbolSubscriber() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainViewModel.currencySymbolViewState.collectLatest { state->
                if (state.error != "") {
                    Toast.makeText(requireContext(), "ErrorSy :${state.error}", Toast.LENGTH_LONG).show()
                } else {
                    state.currencySymbols?.let {
                        Toast.makeText(requireContext(), "SuccessSy :${state.currencySymbols}", Toast.LENGTH_LONG).show()
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