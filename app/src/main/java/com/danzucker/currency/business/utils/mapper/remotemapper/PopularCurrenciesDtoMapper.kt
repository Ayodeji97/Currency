package com.danzucker.currency.business.utils.mapper.remotemapper

import com.danzucker.currency.business.datasource.remote.model.popular.PopularCurrenciesDto
import com.danzucker.currency.business.domain.model.popular.PopularCurrencies
import com.danzucker.currency.business.utils.mapper.base.BaseDtoMapper
import javax.inject.Inject

class PopularCurrenciesDtoMapper @Inject constructor() :
    BaseDtoMapper<PopularCurrenciesDto, PopularCurrencies> {
    override fun transformToDomain(type: PopularCurrenciesDto): PopularCurrencies =
        PopularCurrencies(
            base = type.base ?: "",
            date = type.date ?: "",
            rates = type.rates ?: hashMapOf(),
            success = type.success ?: false,
            timestamp = type.timestamp ?: 0L,
        )
}
