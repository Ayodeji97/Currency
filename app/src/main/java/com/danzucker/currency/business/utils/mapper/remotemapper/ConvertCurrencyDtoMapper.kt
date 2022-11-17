package com.danzucker.currency.business.utils.mapper.remotemapper

import com.danzucker.currency.business.datasource.remote.model.convert.ConvertCurrencyDto
import com.danzucker.currency.business.domain.model.convert.ConvertCurrency
import com.danzucker.currency.business.utils.mapper.base.BaseDtoMapper
import javax.inject.Inject

class ConvertCurrencyDtoMapper @Inject constructor() :
    BaseDtoMapper<ConvertCurrencyDto, ConvertCurrency> {
    override fun transformToDomain(type: ConvertCurrencyDto): ConvertCurrency =
        ConvertCurrency(
            date = type.date ?: "",
            rate = type.info?.rate ?: 0.0,
            timestamp = type.info?.timestamp ?: 0L,
            amount = type.query?.amount ?: 0.0,
            from = type.query?.from ?: "",
            to = type.query?.to ?: "",
            result = type.result ?: 0.0,
            success = type.success ?: false
        )
}
