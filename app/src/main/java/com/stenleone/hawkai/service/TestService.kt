package com.stenleone.hawkai.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import com.stenleone.hawkai.util.notification.NotificationBuilder

class TestService : Service() {

    lateinit var intent: Intent

    override fun onBind(intent: Intent?): IBinder? {
        if (intent != null) {
            this.intent = intent
        }
        return null
    }

    override fun onCreate() {
        super.onCreate()
        //loopToast()
    }

    private fun loopToast() {

        Handler().postDelayed(
            {
                NotificationBuilder(applicationContext).createDefaultNotify()
                loopToast()
            }, 5000
        )
    }
}