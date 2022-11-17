package com.danzucker.currency.business.repository.popular

import com.danzucker.currency.business.domain.model.popular.PopularCurrencies
import com.danzucker.currency.business.utils.Result
import kotlinx.coroutines.flow.Flow

interface PopularCurrenciesRepository {

    suspend fun getPopularCurrencies(
        base: String,
        symbols: String
    ): Flow<Result<PopularCurrencies>>
}
