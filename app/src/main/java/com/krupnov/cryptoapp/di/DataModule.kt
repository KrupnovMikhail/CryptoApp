package com.krupnov.cryptoapp.di

import android.app.Application
import com.krupnov.cryptoapp.data.database.AppDatabase
import com.krupnov.cryptoapp.data.database.CoinInfoDao
import com.krupnov.cryptoapp.data.repository.CoinRepositoryImpl
import com.krupnov.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}