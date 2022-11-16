package com.danzucker.currency.business.domain.usecase

import com.danzucker.currency.business.domain.model.symbols.CurrencySymbols
import com.danzucker.currency.business.repository.symbols.CurrencySymbolsRepository
import com.danzucker.currency.business.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencySymbolsUseCase @Inject constructor(
    private val currencySymbolsRepository: CurrencySymbolsRepository
) {

    suspend operator fun invoke(): Flow<Result<CurrencySymbols>> =
        currencySymbolsRepository.getCurrencySymbols()

}