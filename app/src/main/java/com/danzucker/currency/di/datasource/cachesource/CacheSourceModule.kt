package com.danzucker.currency.di.datasource.cachesource

import android.app.Application
import androidx.room.Room
import com.danzucker.currency.business.datasource.cache.CurrencyDatabase
import com.danzucker.currency.business.datasource.cache.dao.CurrencySymbolsDao
import com.danzucker.currency.business.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheSourceModule {

    @Singleton
    @Provides
    fun provideAppDb(app: Application): CurrencyDatabase =
        Room
            .databaseBuilder(app, CurrencyDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()

    @Singleton
    @Provides
    fun provideCurrencySymbolsDao(currencyDatabase: CurrencyDatabase): CurrencySymbolsDao =
        currencyDatabase.currencySymbolDao()
}
