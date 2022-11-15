package com.danzucker.currency.business.datasource.remote.utils

import com.danzucker.currency.business.utils.ErrorEntity
import com.danzucker.currency.business.utils.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

//class GeneralErrorHandlerImpl : ErrorHandler {
//    override fun getError(throwable: Throwable): ErrorEntity {
//        return when (throwable) {
//            is IOException -> ErrorEntity.Network
//            is HttpException -> {
//                when(throwable.code()) {
//                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied
//                    else -> {}
//                }
//            }
//            else -> {
//                ErrorEntity.Unknown
//            }
//        }
//    }
//}