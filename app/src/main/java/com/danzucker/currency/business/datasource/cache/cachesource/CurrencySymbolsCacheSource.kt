package com.danzucker.currency.business.datasource.cache.cachesource

import com.danzucker.currency.business.datasource.cache.dao.CurrencySymbolsDao
import com.danzucker.currency.business.datasource.cache.model.CurrencySymbolsEntity
import com.danzucker.currency.business.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencySymbolsCacheSource @Inject constructor(
    private val currencySymbolsDao: CurrencySymbolsDao
) {
    suspend fun saveCurrencySymbols(currencySymbolsEntity: CurrencySymbolsEntity) =
        currencySymbolsDao.insert(currencySymbolsEntity)

    fun getCurrencySymbols(): Flow<CurrencySymbolsEntity?> =
        currencySymbolsDao.getCurrencySymbols()
}