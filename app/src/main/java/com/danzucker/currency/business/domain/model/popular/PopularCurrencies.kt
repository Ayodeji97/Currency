package com.danzucker.currency.business.domain.model.popular

import com.google.gson.annotations.SerializedName

data class PopularCurrencies(
    val base: String,
    val date: String,
    val rates: HashMap<String, Double>,
    val success: Boolean,
    val timestamp: Long
)
