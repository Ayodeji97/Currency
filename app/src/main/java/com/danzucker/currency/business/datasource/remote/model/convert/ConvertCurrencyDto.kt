package com.danzucker.currency.business.datasource.remote.model.convert

import com.google.gson.annotations.SerializedName

data class ConvertCurrencyDto(
    @SerializedName("date")
    val date: String? = null,

    @SerializedName("info")
    val info: InfoDto? = null,

    @SerializedName("query")
    val query: QueryDto? = null,

    @SerializedName("result")
    val result: Double? = null,

    @SerializedName("success")
    val success: Boolean? = null,
)

data class InfoDto(
    @SerializedName("rate")
    val rate: Double? = null,

    @SerializedName("timestamp")
    val timestamp: Long? = null
)

data class QueryDto(
    @SerializedName("amount")
    val amount: Double,

    @SerializedName("from")
    val from: String,

    @SerializedName("to")
    val to: String,
)
