package com.danzucker.currency.business.datasource.remote.remotesource

import com.danzucker.currency.business.datasource.remote.model.convert.ConvertDto

interface ConvertCurrencyRemoteSource {
    suspend fun convertCurrency() : Result<ConvertDto>
}