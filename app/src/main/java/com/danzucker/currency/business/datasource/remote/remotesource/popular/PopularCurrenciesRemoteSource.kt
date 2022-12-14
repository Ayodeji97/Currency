package com.danzucker.currency.business.datasource.remote.remotesource.popular

import com.danzucker.currency.business.datasource.remote.model.popular.PopularCurrenciesDto
import com.danzucker.currency.business.utils.Result

interface PopularCurrenciesRemoteSource {
    suspend fun getPopularCurrencies(
        base: String,
        symbols: String
    ): Result<PopularCurrenciesDto>
}
