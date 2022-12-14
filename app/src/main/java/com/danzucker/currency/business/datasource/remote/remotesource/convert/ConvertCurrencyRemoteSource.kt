package com.danzucker.currency.business.datasource.remote.remotesource.convert

import com.danzucker.currency.business.datasource.remote.model.convert.ConvertCurrencyDto
import com.danzucker.currency.business.utils.Result

interface ConvertCurrencyRemoteSource {
    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: String
    ): Result<ConvertCurrencyDto>
}
