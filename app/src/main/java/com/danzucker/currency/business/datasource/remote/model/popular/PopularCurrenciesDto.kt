package com.danzucker.currency.business.datasource.remote.model.popular

import com.google.gson.annotations.SerializedName

data class PopularCurrenciesDto(
    @SerializedName("base")
    val base: String? = null,

    @SerializedName("date")
    val date: String? = null,

    @SerializedName("rates")
    val rates: HashMap<String, Double>? = null,

    @SerializedName("success")
    val success: Boolean? = null,

    @SerializedName("timestamp")
    val timestamp: Long? = null,
)