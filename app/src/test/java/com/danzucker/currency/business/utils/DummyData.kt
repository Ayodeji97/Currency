package com.danzucker.currency.business.utils

import com.danzucker.currency.business.datasource.remote.model.convert.ConvertCurrencyDto
import com.danzucker.currency.business.datasource.remote.model.convert.InfoDto
import com.danzucker.currency.business.datasource.remote.model.convert.QueryDto
import com.danzucker.currency.business.datasource.remote.model.symbols.CurrencySymbolsDto

object DummyData {

    val info = InfoDto(
        rate = 2.3553,
        timestamp = 23525235
    )

    val queryDto = QueryDto(
        amount = 890.330,
        from = "EUR",
        to = "NGN"
    )

    val convertCurrentDto = ConvertCurrencyDto(
        date = "2022-12-09",
        info = info,
        query = queryDto,
        result = 463.657,
        success = true
    )

    val convertCurrentDto2 = ConvertCurrencyDto(
        date = "2022-12-10",
        info = info,
        query = queryDto,
        result = 42.657,
        success = true
    )

    val currencySymbolDto = CurrencySymbolsDto(
        success = true,
        symbols = hashMapOf("EUR" to "Europe", "AWZ" to "Aruban Florin")
    )

    val convertSymbolDto =  ConvertCurrencyDto(
        date = "2022-12-09",
        info = info,
        query = queryDto,
        result = 111.657,
        success = true
    )


    val convertSymbolDto2 =  ConvertCurrencyDto(
        date = "2022-02-09",
        info = info,
        query = queryDto,
        result = 111.657,
        success = true
    )


}