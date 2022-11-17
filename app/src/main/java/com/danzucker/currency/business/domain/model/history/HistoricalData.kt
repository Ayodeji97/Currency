package com.danzucker.currency.business.domain.model.history

import com.google.gson.annotations.SerializedName

data class HistoricalData(
    val base: String,

    val endDate: String,

    val rates: HashMap<String, HashMap<String, Double>>,

    val startDate: String,

    val success: Boolean,

    val timeSeries: Boolean
) {

    val firstDate: String
        get() = rates.entries.map { it.key }[0]

    val secondDate: String
        get() = rates.entries.map { it.key }[1]

    val thirdDate: String
        get() = rates.entries.map { it.key }[2]

    val firstDateValue: Double
        get() = rates.entries.map { it.value }.get(0).entries.map { it.value }.get(0)

    val secondDateValue: Double
        get() = rates.entries.map { it.value }.get(1).entries.map { it.value }.get(0)

    val thirdDateValue: Double
        get() = rates.entries.map { it.value }.get(2).entries.map { it.value }.get(0)
}
