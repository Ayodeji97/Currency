package com.danzucker.currency.business.mapper.remotemapper

import com.danzucker.currency.business.utils.DummyData
import com.danzucker.currency.business.utils.mapper.remotemapper.HistoricalDataDtoMapper
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HistoricalDataDtoMapperTest {

    private lateinit var historicalDataDtoMapper: HistoricalDataDtoMapper

    @Before
    fun setup() {
        historicalDataDtoMapper = HistoricalDataDtoMapper()
    }

    @Test
    fun `map remote data against domain data returns data are correctly mapped`() = runTest {
        val historicalDataDto = DummyData.historicalDataDto
        val mapperToDomain = historicalDataDtoMapper.transformToDomain(historicalDataDto)
        Truth.assertThat(mapperToDomain.base).isEqualTo(historicalDataDto.base)
    }

    @Test
    fun `map remote data against domain data returns data are incorrectly mapped`() = runTest {
        val historicalDataDto = DummyData.historicalDataDto
        val mapperToDomain = historicalDataDtoMapper.transformToDomain(historicalDataDto)
        Truth.assertThat(DummyData.historicalDataDto2.startDate)
            .isNotEqualTo(mapperToDomain.startDate)
    }
}
