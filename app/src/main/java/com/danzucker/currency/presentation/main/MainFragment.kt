package com.danzucker.currency.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.danzucker.currency.R
import com.danzucker.currency.databinding.FragmentMainBinding
import com.danzucker.currency.presentation.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var currentBinding: FragmentMainBinding? = null
    private val ui get() = currentBinding!!

    private val mainViewModel : MainViewModel by viewModels()

    private var baseSelectedItem: String = ""
    private var baseItemPosition: Int = 0
    private var targetSelectedItem: String = ""
    private var targetItemPosition: Int = 0

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

        baseSelectedItem()
        targetSelectedItem()

        convertCurrency ()

        convertedCurrencySubscriber ()

       ui.swapActionBtn.setOnClickListener {
           swapValues()
       }

        ui.showDetailsNextBtn.setOnClickListener {
            val currentDate = getCurrentDate ()
            val lastThreeDaysDate = getThreeDaysAgo()
        }

    }

    private fun convertCurrency () {
        ui.fragmentMainAmountEt.afterTextChangedDelayed {
            val base = ui.baseCurrencySpinner.text.toString()
            val target = ui.targetCurrencySpinner.text.toString()
            if (base.isNotEmpty() && target.isNotEmpty()){
                getConvertedCurrency()
            } else {
                showSnackBar(requireView(), "Currency not selected")
            }
        }
    }

    private fun getConvertedCurrency() {
        val amount = ui.fragmentMainAmountEt.text.toString()
        mainViewModel.onTriggeredEvent(MainViewEvent.GetConvertCurrencyData(
            baseSelectedItem, targetSelectedItem, amount
        ))
    }

    private fun convertedCurrencySubscriber () {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainViewModel.convertCurrencyViewState.collectLatest { state->
                ui.apply {
                    progressBar.isVisible = state.isLoading
                    if (state.error != "") {
                        showSnackBar(requireView(), state.error)
                    } else {
                        state.convertCurrency?.let {
                            val result = it.result
                            ui.convertRateEt.setText(result.toString())
                        }

                    }
                }

            }
        }
    }


    private fun getCurrencySymbolSubscriber() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainViewModel.currencySymbolViewState.collectLatest { state->
                ui.apply {
                    progressBar.isVisible = state.isLoading
                    if (state.error != "") {
                        showSnackBar(requireView(), state.error)
                    } else {
                        state.currencySymbols?.let {
                            populateSpinner(requireContext(), baseCurrencySpinner, it.symbols)
                            populateSpinner(requireContext(), targetCurrencySpinner, it.symbols)
                        }
                    }
                }
            }
        }
    }


    private fun baseSelectedItem() {
        ui.baseCurrencySpinner.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                baseItemPosition = position
                baseSelectedItem = parent.getItemAtPosition(position).toString()
                val target = ui.targetCurrencySpinner.text.toString()
                val amount = ui.fragmentMainAmountEt.text.toString()
                if (amount.isNotEmpty() && target.isNotEmpty()){
                    getConvertedCurrency()
                }
            }
    }

    private fun targetSelectedItem() {
        ui.targetCurrencySpinner.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                targetSelectedItem = parent.getItemAtPosition(position).toString()
                targetItemPosition = position

                val amount = ui.fragmentMainAmountEt.text.toString()
                val base = ui.baseCurrencySpinner.text.toString()
                if (amount.isNotEmpty() && base.isNotEmpty()){
                    getConvertedCurrency()
                }
            }
    }

    private fun swapValues() {
        ui.baseCurrencySpinner.setSelection(targetItemPosition)
        ui.targetCurrencySpinner.setSelection(baseItemPosition)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        currentBinding = null
    }

}