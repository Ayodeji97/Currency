package com.danzucker.currency.business.mapper.remotemapper

import com.danzucker.currency.business.utils.DummyData
import com.danzucker.currency.business.utils.mapper.remotemapper.PopularCurrenciesDtoMapper
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class PopularCurrenciesDtoMapperTest {

    private lateinit var popularCurrenciesDtoMapper: PopularCurrenciesDtoMapper

    @Before
    fun setup() {
        popularCurrenciesDtoMapper = PopularCurrenciesDtoMapper()
    }

    @Test
    fun `map remote data against domain data returns data are correctly mapped` () = runTest {
        val popularCurrenciesDto = DummyData.popularCurrenciesDto
        val mapperToDomain = popularCurrenciesDtoMapper.transformToDomain(popularCurrenciesDto)
        Truth.assertThat(mapperToDomain.date).isEqualTo(popularCurrenciesDto.date)
    }

    @Test
    fun `map remote data against domain data returns data are incorrectly mapped` () = runTest {
        val popularCurrenciesDto = DummyData.popularCurrenciesDto
        val mapperToDomain = popularCurrenciesDtoMapper.transformToDomain(popularCurrenciesDto)
        Truth.assertThat(DummyData.popularCurrenciesDto2.date).isNotEqualTo(mapperToDomain.date)
    }


}