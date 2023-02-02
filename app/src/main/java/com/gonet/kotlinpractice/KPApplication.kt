package com.gonet.kotlinpractice

import android.app.Application
import com.gonet.kotlinpractice.ki.viewModelsModule
import com.gonet.kotlinpractice.services.ki.repositoriesModule
import com.gonet.kotlinpractice.services.networkModule
import com.gonet.kotlinpractice.storage.ki.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KPApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KPApplication)
            modules(listOf(
                networkModule,
                storageModule,
                repositoriesModule,
                viewModelsModule
            ))
        }
    }
}