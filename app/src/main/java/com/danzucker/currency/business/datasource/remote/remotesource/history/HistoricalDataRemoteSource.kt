package com.danzucker.currency.business.datasource.remote.remotesource.history

import com.danzucker.currency.business.datasource.remote.model.historical.HistoricalDataDto
import com.danzucker.currency.business.utils.Result

interface HistoricalDataRemoteSource {
    suspend fun getHistoricalData(
        startDate: String,
        endDate: String,
        from: String,
        to: String,
    ): Result<HistoricalDataDto>
}
