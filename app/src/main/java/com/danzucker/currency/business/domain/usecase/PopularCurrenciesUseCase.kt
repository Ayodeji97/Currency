package com.danzucker.currency.business.domain.usecase

import com.danzucker.currency.business.domain.model.popular.PopularCurrencies
import com.danzucker.currency.business.repository.popular.PopularCurrenciesRepository
import com.danzucker.currency.business.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularCurrenciesUseCase @Inject constructor(
    private val popularCurrenciesRepository: PopularCurrenciesRepository
) {

    suspend operator fun invoke(
        base: String,
        symbols: String
    ): Flow<Result<PopularCurrencies>> =
        popularCurrenciesRepository.getPopularCurrencies(base, symbols)
}
