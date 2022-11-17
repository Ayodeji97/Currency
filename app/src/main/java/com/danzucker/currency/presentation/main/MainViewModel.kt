package com.danzucker.currency.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danzucker.currency.business.domain.usecase.ConvertCurrencyUseCase
import com.danzucker.currency.business.domain.usecase.CurrencySymbolsFromDbUseCase
import com.danzucker.currency.business.domain.usecase.CurrencySymbolsUseCase
import com.danzucker.currency.business.utils.Result
import com.danzucker.currency.business.utils.mapper.remotemapper.CurrencySymbolsDtoMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase,
    private val currencySymbolsUseCase: CurrencySymbolsUseCase,
    private val currencySymbolsFromDbUseCase: CurrencySymbolsFromDbUseCase,
    private val currencySymbolsDtoMapper: CurrencySymbolsDtoMapper,
) : ViewModel() {

    private var _currencySymbolViewState = MutableStateFlow(CurrencySymbolsState())
    val currencySymbolViewState = _currencySymbolViewState.asStateFlow()

    private var _convertCurrencyViewState = MutableStateFlow(MainViewState())
    val convertCurrencyViewState = _convertCurrencyViewState.asStateFlow()

    init {
        onTriggeredEvent(MainViewEvent.GetCurrencySymbols)
    }

    fun onTriggeredEvent(event: MainViewEvent) {
        when (event) {
            is MainViewEvent.GetConvertCurrencyData -> {
                getConvertedCurrency(event.from, event.to, event.amount)
            }
            MainViewEvent.GetCurrencySymbols -> {
                getCurrencyFromDb()
            }
        }
    }

    private fun getConvertedCurrency(
        from: String,
        to: String,
        amount: String
    ) {
        viewModelScope.launch {
            _convertCurrencyViewState.value.let { state ->
                _convertCurrencyViewState.value = state.copy(isLoading = true)
                convertCurrencyUseCase.invoke(from, to, amount).collect {
                    when (it) {
                        is Result.Success -> {
                            _convertCurrencyViewState.value =
                                state.copy(isLoading = false, convertCurrency = it.data)
                        }
                        is Result.Error -> {
                            _convertCurrencyViewState.value =
                                state.copy(isLoading = false, error = it.errorMessage)
                        }
                    }
                }
            }
        }
    }

    private fun getCurrencyFromDb() {
        viewModelScope.launch {
            _currencySymbolViewState.value.let { state ->
                currencySymbolsFromDbUseCase.getCurrencyFromDb().collect {
                    if (it != null) {
                        val currencySymbols = currencySymbolsDtoMapper.transformToDomain(it)
                        _currencySymbolViewState.value =
                            state.copy(currencySymbols = currencySymbols)
                    } else {
                        getCurrencySymbols()
                    }
                }
            }
        }
    }

    private fun getCurrencySymbols() {
        viewModelScope.launch {
            _currencySymbolViewState.value.let { state ->
                _currencySymbolViewState.value = state.copy(isLoading = true)
                currencySymbolsUseCase.invoke().collect {
                    when (it) {
                        is Result.Success ->
                            _currencySymbolViewState.value =
                                state.copy(isLoading = false, currencySymbols = it.data)

                        is Result.Error ->
                            _currencySymbolViewState.value =
                                state.copy(isLoading = false, error = it.errorMessage)
                    }
                }
            }
        }
    }
}
