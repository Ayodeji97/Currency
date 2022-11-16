package com.danzucker.currency.business.datasource.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.danzucker.currency.business.datasource.cache.dao.CurrencySymbolsDao
import com.danzucker.currency.business.datasource.cache.model.CurrencySymbolsEntity
import com.danzucker.currency.business.datasource.remote.utils.Converters

@Database(
    entities = [CurrencySymbolsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencySymbolDao(): CurrencySymbolsDao
}