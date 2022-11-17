package com.danzucker.currency.business.repository.popular

import com.danzucker.currency.business.datasource.remote.remotesource.popular.PopularCurrenciesRemoteSource
import com.danzucker.currency.business.domain.model.popular.PopularCurrencies
import com.danzucker.currency.business.utils.Result
import com.danzucker.currency.business.utils.mapper.remotemapper.PopularCurrenciesDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PopularCurrenciesRepositoryImpl @Inject constructor(
    private val popularCurrenciesRemoteSource: PopularCurrenciesRemoteSource,
    private val popularCurrenciesDtoMapper: PopularCurrenciesDtoMapper
) : PopularCurrenciesRepository {
    override suspend fun getPopularCurrencies(
        base: String,
        symbols: String
    ): Flow<Result<PopularCurrencies>> =
        flow {
            when (
                val response =
                    popularCurrenciesRemoteSource.getPopularCurrencies(base, symbols)
            ) {
                is Result.Success -> {
                    response.data?.let {
                        val popularCurrencies = popularCurrenciesDtoMapper.transformToDomain(it)
                        emit(Result.Success(popularCurrencies))
                    }
                }
                is Result.Error -> {
                    emit(Result.Error(response.errorMessage))
                }
            }
        }
}
