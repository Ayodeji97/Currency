package com.danzucker.currency.business.datasource.remote.remotesource.convert

import com.danzucker.currency.business.datasource.remote.CurrencyApiService
import com.danzucker.currency.business.datasource.remote.model.convert.ConvertCurrencyDto
import com.danzucker.currency.business.utils.Result
import com.danzucker.currency.di.dispatcher.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ConvertCurrencyRemoteSourceImpl @Inject constructor(
    private val currencyApiService: CurrencyApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ConvertCurrencyRemoteSource {
    override suspend fun convertCurrency(
        from: String,
        to: String,
        amount: String
    ): Result<ConvertCurrencyDto> =
        withContext(ioDispatcher) {
            return@withContext try {
                val apiResponse = currencyApiService.convertCurrency(from, to, amount)
                if (apiResponse.isSuccessful) {
                    val currencyInfo = apiResponse.body()
                    Result.Success(currencyInfo)
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