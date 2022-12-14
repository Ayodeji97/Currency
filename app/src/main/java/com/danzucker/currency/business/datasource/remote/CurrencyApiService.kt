package com.danzucker.currency.business.datasource.remote

import com.danzucker.currency.BuildConfig
import com.danzucker.currency.business.datasource.remote.model.convert.ConvertCurrencyDto
import com.danzucker.currency.business.datasource.remote.model.historical.HistoricalDataDto
import com.danzucker.currency.business.datasource.remote.model.popular.PopularCurrenciesDto
import com.danzucker.currency.business.datasource.remote.model.symbols.CurrencySymbolsDto
import com.danzucker.currency.business.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CurrencyApiService {

    @Headers("apikey:$API_KEY")
    @GET("symbols")
    suspend fun getCurrencySymbols(): Response<CurrencySymbolsDto>

    @Headers("apikey:$API_KEY")
    @GET("convert")
    suspend fun convertCurrency(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: String,
    ): Response<ConvertCurrencyDto>

    @Headers("apikey:$API_KEY")
    @GET("timeseries")
    suspend fun getHistoricalData(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("base") from: String,
        @Query("symbols") to: String,
    ): Response<HistoricalDataDto>

    @Headers("apikey:$API_KEY")
    @GET("latest")
    suspend fun getPopularCurrencies(
        @Query("base") base: String,
        @Query("symbols") symbols: String,
    ): Response<PopularCurrenciesDto>
}
