package com.danzucker.currency.business.domain.model.convert

import com.google.gson.annotations.SerializedName

data class ConvertCurrency(
    val date: String,
    val rate: Double,
    val timestamp: Long,
    val amount: Double,
    val from: String,
    val to: String,
    val result: Double,
    val success: Boolean,
)

data class Info(
    @SerializedName("rate")
    val rate: Double? = null,

    @SerializedName("timestamp")
    val timestamp: Long? = null
)

data class Query(
    @SerializedName("amount")
    val amount: Double,

    @SerializedName("from")
    val from: String,

    @SerializedName("to")
    val to: String,
)
