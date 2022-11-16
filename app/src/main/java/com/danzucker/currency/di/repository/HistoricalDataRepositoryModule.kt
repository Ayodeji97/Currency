package com.danzucker.currency.di.repository

import com.danzucker.currency.business.datasource.remote.remotesource.history.HistoricalDataRemoteSourceImpl
import com.danzucker.currency.business.repository.history.HistoricalDataRepository
import com.danzucker.currency.business.repository.history.HistoricalDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HistoricalDataRepositoryModule {
    @Binds
    abstract fun provideHistoricalDataRepository(
        historicalDataRepositoryImpl: HistoricalDataRepositoryImpl
    ): HistoricalDataRepository
}