package com.danzucker.currency.business.utils.mapper.cachemapper

import com.danzucker.currency.business.datasource.cache.model.CurrencySymbolsEntity
import com.danzucker.currency.business.datasource.remote.model.symbols.CurrencySymbolsDto
import com.danzucker.currency.business.utils.mapper.base.BaseEntityMapper
import javax.inject.Inject

class CurrencySymbolsEntityMapper @Inject constructor() :
    BaseEntityMapper<CurrencySymbolsDto, CurrencySymbolsEntity> {
    override fun transformToEntity(type: CurrencySymbolsDto): CurrencySymbolsEntity =
        CurrencySymbolsEntity(
            id = 0,
            symbols = type.symbols?.map {
                it.key
            } ?: listOf()
        )
}
