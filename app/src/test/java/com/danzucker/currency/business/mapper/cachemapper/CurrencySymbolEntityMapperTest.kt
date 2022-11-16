package com.danzucker.currency.business.mapper.cachemapper

import com.danzucker.currency.business.utils.DummyData
import com.danzucker.currency.business.utils.mapper.cachemapper.CurrencySymbolsEntityMapper
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CurrencySymbolEntityMapperTest {

    private lateinit var currencySymbolsEntityMapper: CurrencySymbolsEntityMapper

    @Before
    fun setup() {
        currencySymbolsEntityMapper = CurrencySymbolsEntityMapper()
    }

    @Test
    fun `map remote data against domain data returns data are correctly mapped` () = runTest {
        val currencySymbolDto = DummyData.currencySymbolDto
        val mapperToDomain = currencySymbolsEntityMapper.transformToEntity(currencySymbolDto)
        Truth.assertThat(mapperToDomain.symbols.first()).isEqualTo(currencySymbolDto.symbols?.keys?.first())
    }

    @Test
    fun `map remote data against domain data returns data are incorrectly mapped` () = runTest {
        val currencySymbolDto = DummyData.currencySymbolDto
        val mapperToDomain = currencySymbolsEntityMapper.transformToEntity(currencySymbolDto)
        Truth.assertThat(DummyData.currencySymbolsEntity.symbols.last()).isNotEqualTo(mapperToDomain.symbols.last())
    }

}
