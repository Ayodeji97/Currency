package com.danzucker.currency.business.datasource.remote.remotesource.symbols

import com.danzucker.currency.business.datasource.remote.CurrencyApiService
import com.danzucker.currency.business.utils.MainCoroutineRule
import com.danzucker.currency.business.utils.TestConstants
import com.google.common.truth.Truth
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

class CurrencySymbolsRemoteSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var currencySymbolsRemoteSource: CurrencySymbolsRemoteSource
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

        currencySymbolsRemoteSource =
            CurrencySymbolsRemoteSourceImpl(currencyApiService, Dispatchers.Main)
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
    fun `check that getCurrencySymbols method is successful`() = runBlocking {
        enqueueMockResponse(TestConstants.SYMBOLS_JSON)
        val responseSuccessful: Boolean = currencyApiService.getCurrencySymbols().isSuccessful
        Truth.assertThat(responseSuccessful).isTrue()
    }

    @Test
    fun `check that getCurrencySymbols method does not return null`() = runBlocking {
        enqueueMockResponse(TestConstants.CONVERT_CURRENCY)
        val response = currencyApiService.getCurrencySymbols().body()
        Truth.assertThat(response).isNotNull()
    }
}
