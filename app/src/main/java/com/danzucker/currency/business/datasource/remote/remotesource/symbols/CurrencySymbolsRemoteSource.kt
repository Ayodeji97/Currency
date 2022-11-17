package com.danzucker.currency.business.datasource.remote.remotesource.symbols

import com.danzucker.currency.business.datasource.remote.model.symbols.CurrencySymbolsDto
import com.danzucker.currency.business.utils.Result

interface CurrencySymbolsRemoteSource {

    suspend fun getCurrencySymbols(): Result<CurrencySymbolsDto>
}
