package com.danzucker.currency.business.repository.convert

import com.danzucker.currency.business.domain.model.convert.ConvertCurrency
import com.danzucker.currency.business.utils.Result
import kotlinx.coroutines.flow.Flow

interface ConvertCurrencyRepository {
    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: String
    ): Flow<Result<ConvertCurrency>>
}
