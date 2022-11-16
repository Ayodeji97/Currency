package com.danzucker.currency.business.repository.symbols

import com.danzucker.currency.business.datasource.remote.remotesource.symbols.CurrencySymbolsRemoteSource
import javax.inject.Inject

class CurrencySymbolsRepositoryImpl @Inject constructor(
    private val currencySymbolsRemoteSource: CurrencySymbolsRemoteSource
){
}