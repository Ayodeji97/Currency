package com.danzucker.currency.business.repository.symbols

import com.danzucker.currency.business.datasource.cache.model.CurrencySymbolsEntity
import kotlinx.coroutines.flow.Flow

interface CurrencySymbolsRepository {
    fun getCurrencySymbols () : Flow<Result<CurrencySymbolsEntity>>
}