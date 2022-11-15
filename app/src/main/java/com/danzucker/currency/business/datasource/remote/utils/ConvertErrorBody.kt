package com.danzucker.currency.business.datasource.remote.utils

import com.google.gson.Gson
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody

fun convertErrorBody(errorBody: ResponseBody?): GenericErrorEntity? {
    return try {
        errorBody?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(GenericErrorEntity::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}
