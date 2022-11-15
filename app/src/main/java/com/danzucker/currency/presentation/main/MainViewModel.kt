package com.danzucker.currency.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danzucker.currency.business.domain.usecase.ConvertCurrencyUseCase
import com.danzucker.currency.business.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {

    private var _convertCurrencyViewState = MutableStateFlow(MainViewState())
    val convertCurrencyViewState = _convertCurrencyViewState.asStateFlow()


    fun onTriggeredEvent (event: MainViewEvent) {
        when(event) {
            is MainViewEvent.GetConvertCurrencyData -> {
                getConvertedCurrency(event.from, event.to, event.amount)
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
}