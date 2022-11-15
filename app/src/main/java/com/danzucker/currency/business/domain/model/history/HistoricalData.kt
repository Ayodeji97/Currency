package com.danzucker.currency.business.domain.model.history

import com.google.gson.annotations.SerializedName

data class HistoricalData(
    val base: String,

    val endDate: String,

    val rates: HashMap<String, HashMap<String, Double>>,

    val startDate: String,

    val success: Boolean,

    val timeSeries: Boolean
)
