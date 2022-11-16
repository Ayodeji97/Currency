package com.danzucker.currency.di.repository

import com.danzucker.currency.business.repository.symbols.CurrencySymbolsRepository
import com.danzucker.currency.business.repository.symbols.CurrencySymbolsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CurrencySymbolsRepositoryModule {

    @Binds
    abstract fun provideCurrencySymbolsDataRepository(
        currencySymbolsRepositoryImpl: CurrencySymbolsRepositoryImpl
    ): CurrencySymbolsRepository

}