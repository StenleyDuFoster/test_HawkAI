package com.stenleone.hawkai

import android.app.Application
import android.content.Context
import com.stenleone.hawkai.di.component.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        appContext = applicationContext
        startKoin {
            androidContext(appContext)
            modules(appComponent)
        }
    }
}