package com.danzucker.currency.business.datasource.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danzucker.currency.business.datasource.cache.model.CurrencySymbolsEntity
import com.danzucker.currency.business.utils.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencySymbolsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currencySymbolsEntity: CurrencySymbolsEntity)

    @Query("SELECT * FROM currency_table")
    fun getCurrencySymbols() : Flow<Result<CurrencySymbolsEntity?>>

}