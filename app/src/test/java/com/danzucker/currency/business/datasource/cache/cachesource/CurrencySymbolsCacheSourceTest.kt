package com.danzucker.currency.business.datasource.cache.cachesource

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.danzucker.currency.business.datasource.cache.CurrencyDatabase
import com.danzucker.currency.business.datasource.cache.dao.CurrencySymbolsDao
import com.danzucker.currency.business.utils.DummyData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class CurrencySymbolsCacheSourceTest {

    private lateinit var currencyDb: CurrencyDatabase
    private val context: Context = ApplicationProvider.getApplicationContext()
    private lateinit var currencySymbolsDao: CurrencySymbolsDao

    @Before
    fun initDb() {
        currencyDb = Room.inMemoryDatabaseBuilder(
            context,
            CurrencyDatabase::class.java
        ).allowMainThreadQueries().build()

        currencySymbolsDao = currencyDb.currencySymbolDao()

    }

    @Test
    fun `is currency symbols saved on db`() = runBlocking {
        currencySymbolsDao.insert(DummyData.currencySymbolsEntity)
        val currencySymbol = currencySymbolsDao.getCurrencySymbols()
        assertThat(currencySymbol).isNotNull()
    }

    @Test
    fun `is currency symbols  on db correctly saved in db`() = runBlocking {
        currencySymbolsDao.insert(DummyData.currencySymbolsEntity)
        val currencySymbol = currencySymbolsDao.getCurrencySymbols()
        assertThat(currencySymbol.first()?.symbols?.first()).isEqualTo(DummyData.currencySymbolsEntity.symbols.first())
        assertThat(currencySymbol.first()?.symbols?.last()).isEqualTo(DummyData.currencySymbolsEntity.symbols.last())
    }

    @After
    fun tearDb() {
        currencyDb.close()
    }
}