package com.danzucker.currency.business.datasource.remote.remotesource

import com.danzucker.currency.business.datasource.remote.CurrencyApiService
import com.danzucker.currency.business.datasource.remote.model.convert.ConvertDto
import javax.inject.Inject

class ConvertCurrencyRemoteSourceImpl @Inject constructor(
    private val currencyApiService: CurrencyApiService
) : ConvertCurrencyRemoteSource {
    override suspend fun convertCurrency(): Result<ConvertDto> {
        TODO("Not yet implemented")
    }
}