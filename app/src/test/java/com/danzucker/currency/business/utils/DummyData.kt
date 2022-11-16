package com.danzucker.currency.business.utils

import com.danzucker.currency.business.datasource.remote.model.convert.ConvertCurrencyDto
import com.danzucker.currency.business.datasource.remote.model.convert.InfoDto
import com.danzucker.currency.business.datasource.remote.model.convert.QueryDto

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


}