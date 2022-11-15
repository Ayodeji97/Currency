package com.danzucker.currency.business.domain.usecase

import com.danzucker.currency.business.domain.model.history.HistoricalData
import com.danzucker.currency.business.repository.history.HistoricalDataRepository
import com.danzucker.currency.business.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistoricalDataUseCase @Inject constructor(
    private val historicalDataRepository: HistoricalDataRepository
) {
    suspend operator fun invoke(
        startDate: String,
        endDate: String
    ): Flow<Result<HistoricalData>> =
        historicalDataRepository.getHistoricalData(startDate, endDate)
}