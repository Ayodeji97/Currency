package com.danzucker.currency.business.domain.usecase

import com.danzucker.currency.business.datasource.cache.model.CurrencySymbolsEntity
import com.danzucker.currency.business.repository.symbols.CurrencySymbolsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencySymbolsFromDbUseCase @Inject constructor(
    private val currencySymbolsRepository: CurrencySymbolsRepository
) {

    fun getCurrencyFromDb(): Flow<CurrencySymbolsEntity?> =
        currencySymbolsRepository.getCurrencySymbolsFromDb()
}
