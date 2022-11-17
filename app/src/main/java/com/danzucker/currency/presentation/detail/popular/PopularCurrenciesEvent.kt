package com.danzucker.currency.presentation.detail.popular

sealed class PopularCurrenciesEvent {
    data class GetPopularCurrencies(
        val base: String,
        val symbols: String
    ) : PopularCurrenciesEvent()
}
