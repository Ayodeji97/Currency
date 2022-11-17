package com.danzucker.currency.business.utils.mapper.remotemapper

import com.danzucker.currency.business.datasource.cache.model.CurrencySymbolsEntity
import com.danzucker.currency.business.domain.model.symbols.CurrencySymbols
import com.danzucker.currency.business.utils.mapper.base.BaseDtoMapper
import javax.inject.Inject

class CurrencySymbolsDtoMapper @Inject constructor() :
    BaseDtoMapper<CurrencySymbolsEntity, CurrencySymbols> {
    override fun transformToDomain(type: CurrencySymbolsEntity): CurrencySymbols =
        CurrencySymbols(
            symbols = type.symbols
        )
}
