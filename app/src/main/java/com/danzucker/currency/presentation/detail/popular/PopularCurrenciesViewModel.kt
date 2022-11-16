package com.danzucker.currency.presentation.detail.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danzucker.currency.business.domain.usecase.PopularCurrenciesUseCase
import com.danzucker.currency.business.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularCurrenciesViewModel @Inject constructor(
    private val popularCurrenciesUseCase: PopularCurrenciesUseCase
) : ViewModel() {

    private var _popularCurrenciesViewState = MutableStateFlow(PopularCurrenciesViewState())
    val popularCurrenciesViewState = _popularCurrenciesViewState.asStateFlow()

    fun onTriggeredEvent(event: PopularCurrenciesEvent) {
        when (event) {
            is PopularCurrenciesEvent.GetPopularCurrencies -> {
                getPopularCurrencies(event.base, event.symbols)
            }
        }
    }

    private fun getPopularCurrencies(base: String, symbols: String) {
        viewModelScope.launch {
            _popularCurrenciesViewState.value.let { state ->
                _popularCurrenciesViewState.value = state.copy(isLoading = false)
                popularCurrenciesUseCase.invoke(base, symbols).collect {
                    when (it) {
                        is Result.Success -> {
                            _popularCurrenciesViewState.value =
                                state.copy(isLoading = false, popularCurrencies = it.data)
                        }
                        is Result.Error -> {
                            _popularCurrenciesViewState.value =
                                state.copy(isLoading = false, error = it.errorMessage)
                        }
                    }
                }

            }
        }
    }
}