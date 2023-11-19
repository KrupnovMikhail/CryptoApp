package com.krupnov.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.krupnov.cryptoapp.data.database.AppDatabase
import com.krupnov.cryptoapp.data.mapper.CoinMapper
import com.krupnov.cryptoapp.data.network.ApiFactory
import com.krupnov.cryptoapp.data.workers.RefreshDataWorkerFactory
import com.krupnov.cryptoapp.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: RefreshDataWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}