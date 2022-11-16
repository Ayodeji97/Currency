package com.danzucker.currency.business.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danzucker.currency.business.utils.Constants.CURRENCY_TABLE

@Entity(tableName = CURRENCY_TABLE)
data class CurrencySymbolsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "symbols")
    val symbols : HashMap<String, String>

)
