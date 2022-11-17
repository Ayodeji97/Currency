package com.danzucker.currency.di.repository

import com.danzucker.currency.business.repository.popular.PopularCurrenciesRepository
import com.danzucker.currency.business.repository.popular.PopularCurrenciesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PopularCurrenciesRepositoryModule {

    @Binds
    abstract fun providePopularCurrenciesRepository(
        popularCurrenciesRepositoryImpl: PopularCurrenciesRepositoryImpl
    ): PopularCurrenciesRepository
}
