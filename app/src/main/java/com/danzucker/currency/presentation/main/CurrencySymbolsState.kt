package com.danzucker.currency.presentation.main

import com.danzucker.currency.business.domain.model.symbols.CurrencySymbols

data class CurrencySymbolsState(
    val isLoading: Boolean = false,
    val currencySymbols: CurrencySymbols? = null,
    val error: String = ""
)
