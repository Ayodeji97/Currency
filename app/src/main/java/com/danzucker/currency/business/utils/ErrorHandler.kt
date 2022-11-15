package com.danzucker.currency.business.utils

import com.danzucker.currency.business.utils.ErrorEntity

interface ErrorHandler {

    fun getError(throwable: Throwable): ErrorEntity

}