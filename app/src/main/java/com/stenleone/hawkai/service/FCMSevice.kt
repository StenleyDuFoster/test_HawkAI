package com.stenleone.hawkai.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.stenleone.hawkai.R
import com.stenleone.hawkai.di.application.App
import com.stenleone.hawkai.util.glide.GlideApp
import kotlin.random.Random

class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        showNotification(this, remoteMessage)
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    private fun showNotification(context: Context, remoteMessage: RemoteMessage) {

        val channelId = context.getString(R.string.chanel_id)
        val channelName = context.getString(R.string.chanel_name)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }

        val builtNotification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(remoteMessage.notification?.title)
            .setContentText(remoteMessage.notification?.body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(remoteMessage.notification?.body))
            .setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
            .setSmallIcon(R.drawable.load_drawable)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setWhen(System.currentTimeMillis())
            .setShowWhen(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setVibrate(longArrayOf(500, 500, 500, 500))
            .setAutoCancel(true)

        if(remoteMessage.notification?.imageUrl != null) {

            val imageBitmap = GlideApp.with(App.appContext)
                .asBitmap()
                .load(remoteMessage.notification?.imageUrl!!)
                .into(100,100)
                .get()

            builtNotification.setLargeIcon(imageBitmap)
        }

        notificationManager.notify(Random.nextInt(), builtNotification.build())
    }
}