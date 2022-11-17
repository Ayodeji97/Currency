package com.danzucker.currency.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danzucker.currency.business.domain.usecase.HistoricalDataUseCase
import com.danzucker.currency.business.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val historicalDataUseCase: HistoricalDataUseCase
) : ViewModel() {

    private var _historicalDataViewState = MutableStateFlow(DetailViewState())
    val historicalDataViewState = _historicalDataViewState.asStateFlow()

    fun onTriggeredEvent(event: DetailViewEvent) {
        when (event) {
            is DetailViewEvent.GetHistoricalCurrencyData ->
                getHistoricalCurrencyData(
                    event.startDate, event.endDate,
                    event.from, event.to
                )
        }
    }

    private fun getHistoricalCurrencyData(
        startDate: String,
        endDate: String,
        from: String,
        to: String,
    ) {
        viewModelScope.launch {
            _historicalDataViewState.value.let { state ->
                _historicalDataViewState.value = state.copy(isLoading = true)
                historicalDataUseCase.invoke(startDate, endDate, from, to).collect {
                    when (it) {
                        is Result.Success -> {
                            _historicalDataViewState.value =
                                state.copy(isLoading = false, historicalData = it.data)
                        }
                        is Result.Error -> {
                            _historicalDataViewState.value =
                                state.copy(isLoading = false, error = it.errorMessage)
                        }
                    }
                }
            }
        }
    }
}
