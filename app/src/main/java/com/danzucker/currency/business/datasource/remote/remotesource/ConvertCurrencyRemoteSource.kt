package com.danzucker.currency.business.datasource.remote.remotesource

interface ConvertCurrencyRemoteSource {

    suspend fun convert()
}