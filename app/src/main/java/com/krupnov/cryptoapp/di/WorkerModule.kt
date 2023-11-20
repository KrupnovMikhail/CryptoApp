package com.krupnov.cryptoapp.di

import androidx.work.ListenableWorker
import com.krupnov.cryptoapp.data.workers.ChildWorkerFactory
import com.krupnov.cryptoapp.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataModuleFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}