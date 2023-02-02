package com.gonet.kotlinpractice.storage.ki

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.gonet.kotlinpractice.storage.KPProductsDelegate
import com.gonet.kotlinpractice.storage.KPProductsDelegateImp
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val storageModule = module {
    single {
        provideSharedPreferences(androidApplication())
    }
    single<KPProductsDelegate> { KPProductsDelegateImp(get()) }
}

fun provideSharedPreferences(app: Application): SharedPreferences {
    return app.applicationContext.getSharedPreferences(
        "KP_SHARED_PREF_PRODUCTS",
        Context.MODE_PRIVATE
    )
}