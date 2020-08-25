package com.stenleone.hawkai.di.application

import android.app.Application
import android.content.Context
import com.stenleone.hawkai.di.component.appComponent
import org.koin.android.ext.android.startKoin
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class App : Application() {

    companion object: KoinComponent {
        val appContext: Context by inject()
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin(
            applicationContext,
            appComponent
        )
    }
}