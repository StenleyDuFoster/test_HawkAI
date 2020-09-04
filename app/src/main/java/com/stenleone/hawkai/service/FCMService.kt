package com.stenleone.hawkai.service

import android.annotation.SuppressLint
import android.content.Context
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.stenleone.hawkai.App
import com.stenleone.hawkai.util.glide.GlideApp
import com.stenleone.hawkai.util.notification.NotificationBuilder

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        showNotification(this, remoteMessage)
    }

    private fun showNotification(context: Context, remoteMessage: RemoteMessage) {

        val notificationBuilder = NotificationBuilder(
            context,
            remoteMessage.notification?.title!!,
            remoteMessage.notification?.body!!
        )

        val notificationManager = notificationBuilder.createNotificationManager()
        val notificationBuild = notificationBuilder.createBuiltNotification()

            if (remoteMessage.notification?.imageUrl != null) {

                val imageBitmap = GlideApp.with(App.appContext)
                    .asBitmap()
                    .load(remoteMessage.notification?.imageUrl!!)
                    .into(1000, 1000)
                    .get()

                notificationBuild.setLargeIcon(imageBitmap)
            }
        notificationBuilder.createNotify(notificationManager,notificationBuild)
    }
}