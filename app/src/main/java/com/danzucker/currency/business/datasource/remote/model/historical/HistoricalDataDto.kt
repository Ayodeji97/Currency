package com.danzucker.currency.business.datasource.remote.model.historical

import com.google.gson.annotations.SerializedName

data class HistoricalDataDto(
    @SerializedName("base")
    val base: String? = null,

    @SerializedName("end_date")
    val endDate: String? = null,

    @SerializedName("rates")
    val rates: HashMap<String, HashMap<String, Double>>? = null,

    @SerializedName("start_date")
    val startDate: String? = null,

    @SerializedName("success")
    val success: Boolean? = null,

    @SerializedName("timeseries")
    val timeSeries: Boolean? = null,
)


