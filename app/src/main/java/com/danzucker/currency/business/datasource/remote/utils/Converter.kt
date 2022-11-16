package com.danzucker.currency.business.datasource.remote.utils

import androidx.room.TypeConverter
import com.danzucker.currency.business.datasource.cache.model.CurrencySymbolsEntity
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJsonString(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}

