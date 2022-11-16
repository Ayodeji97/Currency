package com.danzucker.currency.di.datasource.remotesource

import com.danzucker.currency.business.datasource.remote.remotesource.convert.ConvertCurrencyRemoteSource
import com.danzucker.currency.business.datasource.remote.remotesource.convert.ConvertCurrencyRemoteSourceImpl
import com.danzucker.currency.business.datasource.remote.remotesource.history.HistoricalDataRemoteSource
import com.danzucker.currency.business.datasource.remote.remotesource.history.HistoricalDataRemoteSourceImpl
import com.danzucker.currency.business.datasource.remote.remotesource.popular.PopularCurrenciesRemoteSource
import com.danzucker.currency.business.datasource.remote.remotesource.popular.PopularCurrenciesRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteSourceModule {

    @Binds
    abstract fun provideConvertCurrencyRemoteSource(
        convertCurrencyRemoteSourceImpl: ConvertCurrencyRemoteSourceImpl
    ): ConvertCurrencyRemoteSource

    @Binds
    abstract fun provideHistoricalDataRemoteSource(
        historicalDataRemoteSourceImpl: HistoricalDataRemoteSourceImpl
    ): HistoricalDataRemoteSource

    @Binds
    abstract fun providePopularCurrenciesRemoteSource(
        popularCurrenciesRemoteSourceImpl: PopularCurrenciesRemoteSourceImpl
    ): PopularCurrenciesRemoteSource


}