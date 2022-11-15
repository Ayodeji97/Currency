package com.danzucker.currency.business.domain.usecase

import com.danzucker.currency.business.domain.model.convert.ConvertCurrency
import com.danzucker.currency.business.repository.convert.ConvertCurrencyRepository
import com.danzucker.currency.business.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConvertCurrencyUseCase @Inject constructor(
    private val convertCurrencyRepository: ConvertCurrencyRepository
) {
    suspend operator fun invoke(
        from: String,
        to: String,
        amount: String
    ): Flow<Result<ConvertCurrency>> =
        convertCurrencyRepository.convertCurrency(from, to, amount)
}