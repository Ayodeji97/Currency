package com.danzucker.currency.business.datasource.remote

import com.danzucker.currency.business.datasource.remote.model.convert.ConvertDto
import com.danzucker.currency.business.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CurrencyApiService {

    @Headers("Authorization:apikey $API_KEY")
    @GET("convert")
    suspend fun convertCurrency(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: String,
    ) : Response<ConvertDto>
}