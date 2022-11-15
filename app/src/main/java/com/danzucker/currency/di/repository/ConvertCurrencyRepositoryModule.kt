package com.danzucker.currency.di.repository

import com.danzucker.currency.business.repository.convert.ConvertCurrencyRepository
import com.danzucker.currency.business.repository.convert.ConvertCurrencyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ConvertCurrencyRepositoryModule {
    @Binds
    abstract fun provideConvertCurrencyRepository(
        convertCurrencyRepositoryImpl: ConvertCurrencyRepositoryImpl
    ): ConvertCurrencyRepository

}