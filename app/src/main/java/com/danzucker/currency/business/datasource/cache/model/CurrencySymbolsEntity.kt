package com.danzucker.currency.business.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.danzucker.currency.business.datasource.remote.utils.Converters
import com.danzucker.currency.business.utils.Constants.CURRENCY_TABLE

@Entity(tableName = CURRENCY_TABLE)
data class CurrencySymbolsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,


//    @ColumnInfo(name = "symbols")
//    val symbols : HashMap<String, String>? = null
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "symbols")
    val symbols : List<String>

)
