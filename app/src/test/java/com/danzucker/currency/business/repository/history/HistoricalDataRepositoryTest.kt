package com.danzucker.currency.business.repository.history

import com.danzucker.currency.business.datasource.remote.remotesource.history.HistoricalDataRemoteSource
import com.danzucker.currency.business.utils.DummyData
import com.danzucker.currency.business.utils.Result
import com.danzucker.currency.business.utils.TestConstants.FROM
import com.danzucker.currency.business.utils.TestConstants.TEST_END_DATE
import com.danzucker.currency.business.utils.TestConstants.TEST_START_DATE
import com.danzucker.currency.business.utils.TestConstants.TO
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class HistoricalDataRepositoryTest {

    private lateinit var historicalDataRemoteSource: HistoricalDataRemoteSource

    @Before
    fun setup() {
        historicalDataRemoteSource = mock(HistoricalDataRemoteSource::class.java)
    }

    @Test
    fun `check that calling get historical data return converted value`(): Unit = runBlocking {
        `when`(historicalDataRemoteSource.getHistoricalData(TEST_START_DATE, TEST_END_DATE, FROM, TO))
            .thenReturn(
                Result.Success(DummyData.historicalDataDto)
            )

        when (val response = historicalDataRemoteSource.getHistoricalData(TEST_START_DATE, TEST_END_DATE, FROM, TO)) {
            is Result.Success -> {
                Truth.assertThat(response.data?.base).isNotNull()
                Truth.assertThat(response.data?.startDate).isEqualTo(DummyData.historicalDataDto.startDate)
            }
            is Result.Error -> {}
        }
    }

    @Test
    fun `check that calling convert currency return an exception when there is an error`() =
        runBlocking {
            `when`(
                historicalDataRemoteSource.getHistoricalData(TEST_START_DATE, TEST_END_DATE, FROM, TO)
            ).thenReturn(
                Result.Error(DummyData.exception.message ?: "")
            )

            when (val response = historicalDataRemoteSource.getHistoricalData(TEST_START_DATE, TEST_END_DATE, FROM, TO)) {
                is Result.Error -> {
                    Truth.assertThat(response.errorMessage).isEqualTo(DummyData.exception.message)
                    Truth.assertThat(response.errorMessage).isNotNull()
                }
                else -> {}
            }
        }
}
