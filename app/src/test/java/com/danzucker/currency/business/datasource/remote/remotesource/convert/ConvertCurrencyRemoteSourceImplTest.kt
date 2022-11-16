package com.danzucker.currency.business.datasource.remote.remotesource.convert

import com.danzucker.currency.business.datasource.remote.CurrencyApiService
import com.danzucker.currency.business.datasource.remote.model.convert.ConvertCurrencyDto
import com.danzucker.currency.business.utils.MainCoroutineRule
import com.danzucker.currency.business.utils.TestConstants.CONVERT_CURRENCY
import com.danzucker.currency.business.utils.TestConstants.FROM
import com.danzucker.currency.business.utils.TestConstants.TEST_AMOUNT
import com.danzucker.currency.business.utils.TestConstants.TEST_RATE
import com.danzucker.currency.business.utils.TestConstants.TEST_RESULT
import com.danzucker.currency.business.utils.TestConstants.TEST_TIME_STAMP
import com.danzucker.currency.business.utils.TestConstants.TO
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConvertCurrencyRemoteSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var convertCurrencyRemoteSource: ConvertCurrencyRemoteSource
    private lateinit var baseUrl: HttpUrl
    private lateinit var currencyApiService: CurrencyApiService

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/")

        currencyApiService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(CurrencyApiService::class.java)

        convertCurrencyRemoteSource =
            ConvertCurrencyRemoteSourceImpl(currencyApiService, Dispatchers.Main)
    }

    private fun enqueueMockResponse(fileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            mockWebServer.enqueue(mockResponse)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `check that convertCurrency method is successful`() = runBlocking {
        enqueueMockResponse(CONVERT_CURRENCY)
        val responseSuccessful: Boolean = currencyApiService.convertCurrency(
            FROM, TO, TEST_AMOUNT
        ).isSuccessful
        assertThat(responseSuccessful).isTrue()
    }

    @Test
    fun `check that convertCurrency method does not return null`() = runBlocking {
        enqueueMockResponse(CONVERT_CURRENCY)
        val response : ConvertCurrencyDto? = currencyApiService.convertCurrency(FROM, TO, TEST_AMOUNT).body()
        assertThat(response).isNotNull()
    }

    @Test
    fun `check that getCompanyInfo method return at least a corresponding correct data`() =
        runBlocking {
            enqueueMockResponse(CONVERT_CURRENCY)
            val response: ConvertCurrencyDto? = currencyApiService.convertCurrency(FROM, TO, TEST_AMOUNT).body()
            assertThat(response).isNotNull()
            assertThat(response?.result).isEqualTo(TEST_RESULT)
            assertThat(response?.info?.rate).isEqualTo(TEST_RATE)
            assertThat(response?.info?.timestamp).isEqualTo(TEST_TIME_STAMP)
        }

}