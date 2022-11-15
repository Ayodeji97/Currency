package com.danzucker.currency.business.utils

import com.danzucker.currency.business.datasource.remote.utils.GenericErrorEntity
import java.lang.Exception

sealed class Result<out R> {
    data class Success<out T>(val data: T?) : Result<T>()
    data class Error(val errorMessage: GenericErrorEntity) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$errorMessage]"
        }
    }
}