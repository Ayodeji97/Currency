package com.danzucker.currency.business.datasource.remote.model.symbols

import com.google.gson.annotations.SerializedName

data class CurrencySymbolsDto(
    @SerializedName("success")
    val success: Boolean? = null,

    @SerializedName("symbols")
    val symbols: HashMap<String, String>? = null,
)
