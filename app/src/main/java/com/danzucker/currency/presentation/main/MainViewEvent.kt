package com.danzucker.currency.presentation.main

sealed class MainViewEvent {

    data class GetConvertCurrencyData(
        val from: String,
        val to: String,
        val amount: String
    ) : MainViewEvent()
}