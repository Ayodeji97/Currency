package com.danzucker.currency.business.datasource.remote.remotesource.history

import com.danzucker.currency.business.datasource.remote.CurrencyApiService
import com.danzucker.currency.business.datasource.remote.model.historical.HistoricalDataDto
import com.danzucker.currency.business.utils.Result
import com.danzucker.currency.di.dispatcher.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HistoricalDataRemoteSourceImpl @Inject constructor(
    private val currencyApiService: CurrencyApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : HistoricalDataRemoteSource {
    override suspend fun getHistoricalData(
        startDate: String,
        endDate: String
    ): Result<HistoricalDataDto> =
        withContext(ioDispatcher) {
            return@withContext try {
                val apiResponse = currencyApiService.getHistoricalData(startDate, endDate)
                if (apiResponse.isSuccessful) {
                    val historicalData = apiResponse.body()
                    Result.Success(historicalData)
                } else {
                    @Suppress("BlockingMethodInNonBlockingContext")
                    val errorMessageObject = apiResponse.errorBody()?.string()
                    apiResponse.errorBody()?.close()
                    val errorMessage = errorMessageObject?.let {
                        JSONObject(it).getString("message")
                    }
                    Result.Error(errorMessage ?: "Something went wrong")
                }
            } catch (httpException: HttpException) {
                Result.Error(errorMessage = httpException.message())
            } catch (ioException: IOException) {
                Result.Error(
                    errorMessage = ioException.message ?: "Please check your network connection"
                )
            }
        }
}