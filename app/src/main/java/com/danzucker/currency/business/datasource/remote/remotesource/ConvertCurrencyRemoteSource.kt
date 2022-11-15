package com.danzucker.currency.business.datasource.remote.remotesource

import com.danzucker.currency.business.datasource.remote.model.convert.ConvertDto
import com.danzucker.currency.business.utils.Result

interface ConvertCurrencyRemoteSource {
    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: String
    ): Result<ConvertDto>
}