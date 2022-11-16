package com.danzucker.currency.business.utils

import com.danzucker.currency.business.datasource.cache.model.CurrencySymbolsEntity
import com.danzucker.currency.business.datasource.remote.model.convert.ConvertCurrencyDto
import com.danzucker.currency.business.datasource.remote.model.convert.InfoDto
import com.danzucker.currency.business.datasource.remote.model.convert.QueryDto
import com.danzucker.currency.business.datasource.remote.model.historical.HistoricalDataDto
import com.danzucker.currency.business.datasource.remote.model.popular.PopularCurrenciesDto
import com.danzucker.currency.business.datasource.remote.model.symbols.CurrencySymbolsDto

object DummyData {

    val error_text = "Exception thrown"
    val exception = Exception(error_text)

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

    val convertSymbolDto = ConvertCurrencyDto(
        date = "2022-12-09",
        info = info,
        query = queryDto,
        result = 111.657,
        success = true
    )


    val convertSymbolDto2 = ConvertCurrencyDto(
        date = "2022-02-09",
        info = info,
        query = queryDto,
        result = 111.657,
        success = true
    )

    val historicalDataDto = HistoricalDataDto(
        base = "EUR",
        endDate = "2010-04-03",
        rates = hashMapOf(),
        startDate = "2019-07-11",
        success = true,
        timeSeries = true
    )

    val historicalDataDto2 = HistoricalDataDto(
        base = "NGN",
        endDate = "2019-08-11",
        rates = hashMapOf(),
        startDate = "2022-04-10",
        success = true,
        timeSeries = true
    )

    val popularCurrenciesDto = PopularCurrenciesDto(
        base = "NGN",
        date = "2019-08-11",
        rates = hashMapOf(),
        success = true,
        timestamp = 45525
    )

    val popularCurrenciesDto2 = PopularCurrenciesDto(
        base = "NGN",
        date = "2019-08-15",
        rates = hashMapOf(),
        success = true,
        timestamp = 585875
    )

    val currencySymbolsEntity = CurrencySymbolsEntity(
        id = 3,
        symbols = listOf("NGN", "DRT")
    )





}