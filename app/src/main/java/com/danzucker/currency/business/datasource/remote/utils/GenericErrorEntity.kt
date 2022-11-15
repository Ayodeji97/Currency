package com.danzucker.currency.business.datasource.remote.utils

import com.google.gson.annotations.SerializedName

data class GenericErrorEntity(
    @SerializedName("error")
    val error: Error,

    @SerializedName("success")
    val success: Boolean
)

data class Error(
    @SerializedName("code")
    val code: Int,

    @SerializedName("info")
    val info: String,

    @SerializedName("type")
    val type: String,
)