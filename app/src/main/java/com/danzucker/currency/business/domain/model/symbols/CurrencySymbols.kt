package com.danzucker.currency.business.domain.model.symbols

import com.google.gson.annotations.SerializedName

data class CurrencySymbols(
    //val symbols: HashMap<String, String>,
    val symbols: List<String>,
)
