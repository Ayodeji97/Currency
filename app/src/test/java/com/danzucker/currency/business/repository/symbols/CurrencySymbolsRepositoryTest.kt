package com.danzucker.currency.business.repository.symbols

import com.danzucker.currency.business.datasource.remote.remotesource.symbols.CurrencySymbolsRemoteSource
import com.danzucker.currency.business.utils.DummyData
import com.danzucker.currency.business.utils.Result
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class CurrencySymbolsRepositoryTest {

    private lateinit var currencySymbolsRemoteSource: CurrencySymbolsRemoteSource

    @Before
    fun setup() {
        currencySymbolsRemoteSource = mock(CurrencySymbolsRemoteSource::class.java)
    }

    @Test
    fun `check that calling convert currency return converted value`(): Unit = runBlocking {
        `when`(currencySymbolsRemoteSource.getCurrencySymbols())
            .thenReturn(
                Result.Success(DummyData.currencySymbolDto)
            )

        when (val response = currencySymbolsRemoteSource.getCurrencySymbols()) {
            is Result.Success -> {
                Truth.assertThat(response.data?.success).isTrue()
            }
            is Result.Error -> {}
        }
    }

    @Test
    fun `check that calling convert currency return an exception when there is an error`() =
        runBlocking {
            `when`(
                currencySymbolsRemoteSource.getCurrencySymbols()
            ).thenReturn(
                Result.Error(DummyData.exception.message ?: "")
            )

            when (val response = currencySymbolsRemoteSource.getCurrencySymbols()) {
                is Result.Error -> {
                    Truth.assertThat(response.errorMessage).isEqualTo(DummyData.exception.message)
                    Truth.assertThat(response.errorMessage).isNotNull()
                }
                else -> {}
            }
        }
}
