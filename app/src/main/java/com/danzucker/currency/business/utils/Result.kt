package com.danzucker.currency.business.utils

import java.lang.Exception

sealed class Result<out R> {

    data class Success<out T>(val data: T?) : Result<T>()
    data class Error(val errorMessage: String) : Result<Nothing>()
    data class Loading (val isLoading : Boolean) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$errorMessage]"
            is Loading -> "Loading $isLoading"
        }
    }
}