package com.danzucker.currency.business.repository.convert

import com.danzucker.currency.business.datasource.remote.remotesource.convert.ConvertCurrencyRemoteSource
import com.danzucker.currency.business.utils.DummyData
import com.danzucker.currency.business.utils.Result
import com.danzucker.currency.business.utils.TestConstants.FROM
import com.danzucker.currency.business.utils.TestConstants.TEST_AMOUNT
import com.danzucker.currency.business.utils.TestConstants.TO
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ConvertCurrencyRepositoryTest {

    private lateinit var convertCurrencyRemoteSource : ConvertCurrencyRemoteSource

    @Before
    fun setup() {
        convertCurrencyRemoteSource = mock(ConvertCurrencyRemoteSource::class.java)
    }

    @Test
    fun `check that calling convert currency return converted value`(): Unit = runBlocking {
        `when`(convertCurrencyRemoteSource.convertCurrency(FROM, TO, TEST_AMOUNT)).thenReturn(
            Result.Success(DummyData.convertCurrentDto)
        )

        when(val response = convertCurrencyRemoteSource.convertCurrency(FROM, TO, TEST_AMOUNT)) {
            is Result.Success -> {
                assertThat(response.data?.result).isNotNull()
                assertThat(response.data?.result).isEqualTo(DummyData.convertCurrentDto.result)
            }
            is Result.Error -> {}
        }
    }



    @Test
    fun `check that calling convert currency return an exception when there is an error`() =
        runBlocking {
            `when`(convertCurrencyRemoteSource.convertCurrency(FROM, TO, TEST_AMOUNT)).thenReturn(
                Result.Error(DummyData.exception.message ?: "")
            )

            when (val response = convertCurrencyRemoteSource.convertCurrency(FROM, TO, TEST_AMOUNT)) {
                is Result.Error -> {
                    assertThat(response.errorMessage).isEqualTo(DummyData.exception.message)
                    assertThat(response.errorMessage).isNotNull()
                }
                else -> {}
            }
        }

}