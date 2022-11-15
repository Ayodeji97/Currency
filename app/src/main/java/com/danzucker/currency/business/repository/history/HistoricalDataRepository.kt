package com.danzucker.currency.business.repository.history

import com.danzucker.currency.business.domain.model.history.HistoricalData
import com.danzucker.currency.business.utils.Result
import kotlinx.coroutines.flow.Flow

interface HistoricalDataRepository {

    suspend fun getHistoricalData(startDate: String, endDate: String):
            Flow<Result<HistoricalData>>
}