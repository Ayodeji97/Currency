package com.danzucker.currency.business.datasource.remote.remotesource

import com.danzucker.currency.business.datasource.remote.CurrencyApiService
import com.danzucker.currency.business.datasource.remote.model.convert.ConvertDto
import com.danzucker.currency.business.datasource.remote.utils.GenericErrorEntity
import com.danzucker.currency.business.datasource.remote.utils.convertErrorBody
import com.danzucker.currency.business.utils.Result
import com.danzucker.currency.di.dispatcher.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class ConvertCurrencyRemoteSourceImpl @Inject constructor(
    private val currencyApiService: CurrencyApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ConvertCurrencyRemoteSource {
    override suspend fun convertCurrency(
        from: String,
        to: String,
        amount: String
    ): Result<ConvertDto> =
        withContext(ioDispatcher) {
            return@withContext try {
                val apiResponse = currencyApiService.convertCurrency(from, to, amount)
                if (apiResponse.isSuccessful) {
                    val currencyInfo = apiResponse.body()
                    Result.Success(currencyInfo)
                } else {
                    val errorResponse: GenericErrorEntity? =
                        convertErrorBody(errorBody = apiResponse.errorBody())
                    Result.Error(
                        errorMessage = errorResponse?.error?.info ?: "Something went wrong"
                    )
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