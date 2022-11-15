package com.danzucker.currency.di.datasource.remotesource

import com.danzucker.currency.business.datasource.remote.remotesource.ConvertCurrencyRemoteSource
import com.danzucker.currency.business.datasource.remote.remotesource.ConvertCurrencyRemoteSourceImpl
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


}