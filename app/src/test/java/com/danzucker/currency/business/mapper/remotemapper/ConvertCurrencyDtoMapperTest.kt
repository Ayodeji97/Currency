package com.danzucker.currency.business.mapper.remotemapper

import com.danzucker.currency.business.utils.DummyData
import com.danzucker.currency.business.utils.mapper.remotemapper.ConvertCurrencyDtoMapper
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ConvertCurrencyDtoMapperTest {

    private lateinit var convertCurrencyDtoMapper: ConvertCurrencyDtoMapper

    @Before
    fun setup() {
        convertCurrencyDtoMapper = ConvertCurrencyDtoMapper()
    }

    @Test
    fun `map remote data against domain data returns data are correctly mapped` () = runTest {
        val convertCurrentDto = DummyData.convertCurrentDto
        val mapperToDomain = convertCurrencyDtoMapper.transformToDomain(convertCurrentDto)
        assertThat(mapperToDomain.rate).isEqualTo(convertCurrentDto.info?.rate)
    }

    @Test
    fun `map remote data against domain data returns data are incorrectly mapped` () = runTest {
        val convertCurrentDto = DummyData.convertCurrentDto
        val mapperToDomain = convertCurrencyDtoMapper.transformToDomain(convertCurrentDto)
        assertThat(DummyData.convertCurrentDto2.result).isNotEqualTo(mapperToDomain.result)
    }

}