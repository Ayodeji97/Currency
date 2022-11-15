package com.danzucker.currency.business.repository.history

import com.danzucker.currency.business.datasource.remote.remotesource.history.HistoricalDataRemoteSource
import com.danzucker.currency.business.domain.model.history.HistoricalData
import com.danzucker.currency.business.utils.Result
import com.danzucker.currency.business.utils.mapper.remotemapper.HistoricalDataDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HistoricalDataRepositoryImpl @Inject constructor(
    private val historicalDataRemoteSource: HistoricalDataRemoteSource,
    private val historicalDataDtoMapper: HistoricalDataDtoMapper
) : HistoricalDataRepository {
    override suspend fun getHistoricalData(
        startDate: String,
        endDate: String
    ): Flow<Result<HistoricalData>> =
        flow {
            when (val response = historicalDataRemoteSource.getHistoricalData(startDate, endDate)) {
                is Result.Success -> {
                    response.data?.let {
                        val historicalData = historicalDataDtoMapper.transformToDomain(it)
                        emit(Result.Success(historicalData))
                    }
                }
                is Result.Error -> {
                    emit(Result.Error(response.errorMessage))
                }
            }
        }
}