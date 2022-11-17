package com.danzucker.currency.business.mapper.remotemapper

import com.danzucker.currency.business.utils.DummyData
import com.danzucker.currency.business.utils.DummyData.convertCurrentDto
import com.danzucker.currency.business.utils.mapper.remotemapper.ConvertCurrencyDtoMapper
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CurrencySymbolsDtoMapperTest {

    private lateinit var currencySymbolDtoMapper: ConvertCurrencyDtoMapper

    @Before
    fun setup() {
        currencySymbolDtoMapper = ConvertCurrencyDtoMapper()
    }

    @Test
    fun `map remote data against domain data returns data are correctly mapped`() = runTest {
        val convertSymbolDto = DummyData.convertSymbolDto
        val mapperToDomain = currencySymbolDtoMapper.transformToDomain(convertSymbolDto)
        Truth.assertThat(mapperToDomain.rate).isEqualTo(convertCurrentDto.info?.rate)
    }

    @Test
    fun `map remote data against domain data returns data are incorrectly mapped`() = runTest {
        val convertSymbolDto = DummyData.convertSymbolDto
        val mapperToDomain = currencySymbolDtoMapper.transformToDomain(convertSymbolDto)
        Truth.assertThat(DummyData.convertSymbolDto2.date).isNotEqualTo(mapperToDomain.date)
    }
}
