package com.danzucker.currency.presentation.detail

sealed class DetailViewEvent {

    data class GetHistoricalCurrencyData(
        val startDate: String,
        val endDate: String
    ) : DetailViewEvent()
}