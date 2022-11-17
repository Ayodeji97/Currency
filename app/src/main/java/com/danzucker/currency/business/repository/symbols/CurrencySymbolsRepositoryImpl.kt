package com.danzucker.currency.business.repository.symbols

import com.danzucker.currency.business.datasource.cache.cachesource.CurrencySymbolsCacheSource
import com.danzucker.currency.business.datasource.cache.model.CurrencySymbolsEntity
import com.danzucker.currency.business.datasource.remote.remotesource.symbols.CurrencySymbolsRemoteSource
import com.danzucker.currency.business.domain.model.symbols.CurrencySymbols
import com.danzucker.currency.business.utils.Result
import com.danzucker.currency.business.utils.mapper.cachemapper.CurrencySymbolsEntityMapper
import com.danzucker.currency.business.utils.mapper.remotemapper.CurrencySymbolsDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencySymbolsRepositoryImpl @Inject constructor(
    private val currencySymbolsRemoteSource: CurrencySymbolsRemoteSource,
    private val currencySymbolsCacheSource: CurrencySymbolsCacheSource,
    private val currencySymbolsDtoMapper: CurrencySymbolsDtoMapper,
    private val currencySymbolsEntityMapper: CurrencySymbolsEntityMapper
) : CurrencySymbolsRepository {
    override suspend fun getCurrencySymbols(): Flow<Result<CurrencySymbols>> =
        flow {
            when (val response = currencySymbolsRemoteSource.getCurrencySymbols()) {
                is Result.Success -> {
                    response.data?.let {
                        val currencySymbolsEntity =
                            currencySymbolsEntityMapper.transformToEntity(it)
                        currencySymbolsCacheSource.saveCurrencySymbols(currencySymbolsEntity)
                        emit(
                            Result.Success(
                                currencySymbolsDtoMapper.transformToDomain(
                                    currencySymbolsEntity
                                )
                            )
                        )
                    }
                }
                is Result.Error -> {
                    emit(Result.Error(response.errorMessage))
                }
            }
        }

    override fun getCurrencySymbolsFromDb(): Flow<CurrencySymbolsEntity?> =
        currencySymbolsCacheSource.getCurrencySymbols()
}
