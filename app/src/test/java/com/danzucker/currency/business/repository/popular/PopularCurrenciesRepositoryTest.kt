package com.danzucker.currency.business.repository.popular

import com.danzucker.currency.business.datasource.remote.remotesource.convert.ConvertCurrencyRemoteSource
import com.danzucker.currency.business.datasource.remote.remotesource.popular.PopularCurrenciesRemoteSource
import com.danzucker.currency.business.utils.DummyData
import com.danzucker.currency.business.utils.Result
import com.danzucker.currency.business.utils.TestConstants
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class PopularCurrenciesRepositoryTest {

    private lateinit var popularCurrenciesRemoteSource: PopularCurrenciesRemoteSource

    @Before
    fun setup() {
        popularCurrenciesRemoteSource = mock(PopularCurrenciesRemoteSource::class.java)
    }

    @Test
    fun `check that calling get popular currencies return converted value`(): Unit = runBlocking {
        Mockito.`when`(popularCurrenciesRemoteSource.getPopularCurrencies(TestConstants.BASE, TestConstants.SYMBOLS))
            .thenReturn(
            Result.Success(DummyData.popularCurrenciesDto)
        )

        when(val response = popularCurrenciesRemoteSource.getPopularCurrencies(TestConstants.BASE, TestConstants.SYMBOLS)) {
            is Result.Success -> {
                Truth.assertThat(response.data?.base).isNotNull()
                Truth.assertThat(response.data?.date).isEqualTo(DummyData.popularCurrenciesDto.date)
            }
            is Result.Error -> {}
        }
    }

    @Test
    fun `check that calling get popular currencies return an exception when there is an error`() =
        runBlocking {
            `when`(
                popularCurrenciesRemoteSource.getPopularCurrencies(TestConstants.BASE, TestConstants.SYMBOLS)
            ).thenReturn(
                Result.Error(DummyData.exception.message ?: "")
            )

            when (val response = popularCurrenciesRemoteSource.getPopularCurrencies(TestConstants.BASE, TestConstants.SYMBOLS)) {
                is Result.Error -> {
                    Truth.assertThat(response.errorMessage).isEqualTo(DummyData.exception.message)
                    Truth.assertThat(response.errorMessage).isNotNull()
                }
                else -> {}
            }
        }

}