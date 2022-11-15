package com.danzucker.currency.presentation.detail

import com.danzucker.currency.business.domain.model.history.HistoricalData

data class DetailViewState(
    val isLoading: Boolean = false,
    val historicalData: HistoricalData? = null,
    val error: String = ""
)
