package com.danzucker.currency.business.utils.mapper.remotemapper

import com.danzucker.currency.business.datasource.remote.model.historical.HistoricalDataDto
import com.danzucker.currency.business.domain.model.history.HistoricalData
import com.danzucker.currency.business.utils.mapper.base.BaseDtoMapper
import javax.inject.Inject

class HistoricalDataDtoMapper @Inject constructor() : BaseDtoMapper<HistoricalDataDto, HistoricalData> {
    override fun transformToDomain(type: HistoricalDataDto): HistoricalData =
        HistoricalData(
            base = type.base ?: "",
            endDate = type.endDate ?: "",
            rates = type.rates ?: hashMapOf(),
            startDate = type.startDate ?: "",
            success = type.success ?: false,
            timeSeries = type.timeSeries ?: false
        )
}
