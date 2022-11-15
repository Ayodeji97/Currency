package com.danzucker.currency.business.repository.convert

import com.danzucker.currency.business.datasource.remote.remotesource.convert.ConvertCurrencyRemoteSource
import com.danzucker.currency.business.domain.model.convert.ConvertCurrency
import com.danzucker.currency.business.utils.Result
import com.danzucker.currency.business.utils.mapper.remotemapper.ConvertCurrencyDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConvertCurrencyRepositoryImpl @Inject constructor(
    private val convertCurrencyRemoteSource: ConvertCurrencyRemoteSource,
    private val convertCurrencyDtoMapper: ConvertCurrencyDtoMapper
) : ConvertCurrencyRepository {
    override suspend fun convertCurrency(
        from: String,
        to: String,
        amount: String
    ): Flow<Result<ConvertCurrency>> =
        flow {
            when (val response = convertCurrencyRemoteSource.convertCurrency(from, to, amount)) {
                is Result.Success -> {
                    response.data?.let {
                        val convertCurrency = convertCurrencyDtoMapper.transformToDomain(it)
                        emit(Result.Success(convertCurrency))
                    }
                }
                is Result.Error -> {
                    emit(Result.Error(response.errorMessage))
                }
            }
        }

}