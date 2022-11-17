package com.danzucker.currency.business.repository.symbols

import com.danzucker.currency.business.datasource.cache.model.CurrencySymbolsEntity
import com.danzucker.currency.business.domain.model.symbols.CurrencySymbols
import com.danzucker.currency.business.utils.Result
import kotlinx.coroutines.flow.Flow

interface CurrencySymbolsRepository {
    suspend fun getCurrencySymbols(): Flow<Result<CurrencySymbols>>
    fun getCurrencySymbolsFromDb(): Flow<CurrencySymbolsEntity?>
}
