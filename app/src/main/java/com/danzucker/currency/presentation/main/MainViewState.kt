package com.danzucker.currency.presentation.main

import com.danzucker.currency.business.domain.model.convert.ConvertCurrency

data class MainViewState(
    val isLoading: Boolean = false,
    val convertCurrency: ConvertCurrency? = null,
    val error : String = ""
)