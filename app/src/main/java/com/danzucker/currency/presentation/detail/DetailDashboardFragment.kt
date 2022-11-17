package com.danzucker.currency.presentation.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.danzucker.currency.R
import com.danzucker.currency.business.domain.model.history.HistoricalData
import com.danzucker.currency.databinding.FragmentDetailDashboardBinding
import com.danzucker.currency.presentation.detail.popular.PopularCurrenciesEvent
import com.danzucker.currency.presentation.detail.popular.PopularCurrenciesViewModel
import com.danzucker.currency.presentation.utils.ViewConstants.BASE_CURRENCY
import com.danzucker.currency.presentation.utils.ViewConstants.SOME_POPULAR_CURRENCIES
import com.danzucker.currency.presentation.utils.showSnackBar
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailDashboardFragment : Fragment() {

    private var currentBinding: FragmentDetailDashboardBinding? = null
    private val ui get() = currentBinding!!

    private val detailViewModel: DetailViewModel by viewModels()

    private val popularCurrenciesViewModel: PopularCurrenciesViewModel by viewModels()

    private var historicalData: HistoricalData? = null

    private lateinit var barChart: BarChart
    private lateinit var barData: BarData
    private lateinit var barDataSet: BarDataSet

    private lateinit var barEntriesList: ArrayList<BarEntry>

    private lateinit var base: String
    private lateinit var target: String
    private lateinit var currentDate: String
    private lateinit var lastThreeDaysDate: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // return super.onCreateView(inflater, container, savedInstanceState)
        currentBinding = FragmentDetailDashboardBinding.inflate(inflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        base = DetailDashboardFragmentArgs.fromBundle(requireArguments()).base ?: ""
        target = DetailDashboardFragmentArgs.fromBundle(requireArguments()).target ?: ""
        currentDate = DetailDashboardFragmentArgs.fromBundle(requireArguments()).currentDate ?: ""
        lastThreeDaysDate =
            DetailDashboardFragmentArgs.fromBundle(requireArguments()).lastThreeDaysDate ?: ""

        barChart = ui.idBarChart

        getHistoricalCurrencyData()
        getHistoricalCurrencySubscriber()

        getPopularCurrencies()
        getPopularCurrenciesSubscriber()

        // on below line we are initializing our bar data set
    }

    private fun getHistoricalCurrencyData() {
        detailViewModel.onTriggeredEvent(
            DetailViewEvent.GetHistoricalCurrencyData(
                lastThreeDaysDate, currentDate, base, target
            )
        )
    }

    private fun getHistoricalCurrencySubscriber() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            detailViewModel.historicalDataViewState.collectLatest { state ->
                ui.apply {
                    progressBar.isVisible = state.isLoading
                    if (state.error != "") {
                        showSnackBar(requireView(), state.error)
                    } else {
                        state.historicalData?.let {
                            historicalData = state.historicalData
                            getBarChartData()
                            updateHistoricalDataView(state.historicalData)
                        }
                    }
                }
            }
        }
    }

    // For popular currencies
    private fun getPopularCurrencies() {
        popularCurrenciesViewModel.onTriggeredEvent(
            PopularCurrenciesEvent.GetPopularCurrencies(
                BASE_CURRENCY, SOME_POPULAR_CURRENCIES,
            )
        )
    }

    private fun getPopularCurrenciesSubscriber() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            popularCurrenciesViewModel.popularCurrenciesViewState.collectLatest { state ->
                ui.apply {
                    progressBar.isVisible = state.isLoading
                    if (state.error != "") {
                        showSnackBar(requireView(), state.error)
                    } else {
                        state.popularCurrencies?.let {
                            val check =
                                state.popularCurrencies.rates.entries.map { "${it.key} - ${it.value}" }
                            val arrayAdapter: ArrayAdapter<*>
                            arrayAdapter = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_list_item_1, check,
                            )
                            ui.popularCurrenciesList.adapter = arrayAdapter
                        }
                    }
                }
            }
        }
    }

    private fun getBarChartData() {
        barEntriesList = ArrayList()

        barEntriesList.add(BarEntry(2f, 2f))
        barEntriesList.add(BarEntry(3f, 3f))
        barEntriesList.add(BarEntry(4f, 4f))
        barEntriesList.add(BarEntry(5f, 5f))

        barDataSet = BarDataSet(barEntriesList, "Bar Chart Data")
        barData = BarData(barDataSet)
        barChart.data = barData
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.setColor(resources.getColor(R.color.purple_200))
        barDataSet.valueTextSize = 16f
        barChart.description.isEnabled = false

        // to our bar entries list
    }

    private fun updateHistoricalDataView(historicalData: HistoricalData?) {
        ui.apply {
            // Dates
            firstDateTv.text = historicalData?.firstDate
            secondDateTv.text = historicalData?.secondDate
            thirdDateTv.text = historicalData?.thirdDate
            // Values
            firstDateAmountTv.text = historicalData?.firstDateValue.toString()
            secondDateAmountTv.text = historicalData?.secondDateValue.toString()
            thirdDateAmountTv.text = historicalData?.thirdDateValue.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentBinding = null
    }
}
