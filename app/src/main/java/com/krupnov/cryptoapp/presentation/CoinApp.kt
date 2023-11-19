package com.krupnov.cryptoapp.presentation

import android.app.Application
import com.krupnov.cryptoapp.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}