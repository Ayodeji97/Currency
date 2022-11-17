package com.danzucker.currency.presentation.detail.popular

import com.danzucker.currency.business.domain.model.popular.PopularCurrencies

data class PopularCurrenciesViewState(
    val isLoading: Boolean = false,
    val popularCurrencies: PopularCurrencies? = null,
    val error: String = ""
)
