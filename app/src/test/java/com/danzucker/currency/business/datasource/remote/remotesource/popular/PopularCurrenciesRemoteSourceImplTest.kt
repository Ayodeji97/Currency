package com.danzucker.currency.business.datasource.remote.remotesource.popular

import com.danzucker.currency.business.datasource.remote.CurrencyApiService
import com.danzucker.currency.business.datasource.remote.model.convert.ConvertCurrencyDto
import com.danzucker.currency.business.datasource.remote.remotesource.convert.ConvertCurrencyRemoteSource
import com.danzucker.currency.business.datasource.remote.remotesource.convert.ConvertCurrencyRemoteSourceImpl
import com.danzucker.currency.business.utils.MainCoroutineRule
import com.danzucker.currency.business.utils.TestConstants
import com.danzucker.currency.business.utils.TestConstants.FROM
import com.danzucker.currency.business.utils.TestConstants.POPULAR_CURRENCIES
import com.danzucker.currency.business.utils.TestConstants.SYMBOLS
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

class PopularCurrenciesRemoteSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var popularCurrenciesRemoteSource: PopularCurrenciesRemoteSource
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

        popularCurrenciesRemoteSource =
            PopularCurrenciesRemoteSourceImpl(currencyApiService, Dispatchers.Main)

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
    fun `check that getPopularCurrencies method is successful`() = runBlocking {
        enqueueMockResponse(POPULAR_CURRENCIES)
        val responseSuccessful: Boolean = currencyApiService.getPopularCurrencies(
            FROM, SYMBOLS
        ).isSuccessful
        assertThat(responseSuccessful).isTrue()
    }

    @Test
    fun `check that getPopularCurrencies method does not return null`() = runBlocking {
        enqueueMockResponse(POPULAR_CURRENCIES)
        val response = currencyApiService.getPopularCurrencies(
        FROM, SYMBOLS
        ).body()
        assertThat(response).isNotNull()
    }

    @Test
    fun `check that getPopularCurrencies method return at least a corresponding correct data`() =
        runBlocking {
            enqueueMockResponse(POPULAR_CURRENCIES)
            val response = currencyApiService.getPopularCurrencies(
                FROM, SYMBOLS
            ).body()
            assertThat(response).isNotNull()
            assertThat(response?.date).isEqualTo(TestConstants.TEST_START_DATE)
            assertThat(response?.base).isEqualTo(TestConstants.FROM)
        }
}