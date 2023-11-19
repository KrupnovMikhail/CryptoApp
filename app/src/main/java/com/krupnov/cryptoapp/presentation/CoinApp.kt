package com.krupnov.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.krupnov.cryptoapp.data.database.AppDatabase
import com.krupnov.cryptoapp.data.mapper.CoinMapper
import com.krupnov.cryptoapp.data.network.ApiFactory
import com.krupnov.cryptoapp.data.workers.RefreshDataWorkerFactory
import com.krupnov.cryptoapp.di.DaggerApplicationComponent

class CoinApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            )
            .build()
    }
}