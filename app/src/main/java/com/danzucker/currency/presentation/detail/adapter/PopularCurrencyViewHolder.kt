package com.danzucker.currency.presentation.detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.danzucker.currency.business.domain.model.popular.PopularCurrencies
import com.danzucker.currency.databinding.PopularCurrenciesListItemBinding

class PopularCurrencyViewHolder(
    private val ui: PopularCurrenciesListItemBinding,
) : RecyclerView.ViewHolder(ui.root) {

    fun bind (popularCurrencies: PopularCurrencies) {
        ui.apply {
           val see = popularCurrencies.rates.entries.map { it.value }
           val key = popularCurrencies.rates.entries.map { it.key }
            see.forEach {
                popularCurrenciesAmountTv.text = it.toString()
            }
            key.forEach {
                popularCurrenciesSymbolTv.text = it
            }
        }
    }
}