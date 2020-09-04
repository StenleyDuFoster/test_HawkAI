package com.stenleone.hawkai.util.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.stenleone.hawkai.R
import kotlin.random.Random

class NotificationBuilder(
    val context: Context,
    val title: String = "",
    val subTitle: String = "",
    val bigText: String = "",
    val channelId: String = context.getString(R.string.chanel_id),
    val channelName: String = context.getString(R.string.chanel_name)
) {

    fun createNotificationManager(): NotificationManager {

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }
        return notificationManager
    }

    fun createBuiltNotification(): NotificationCompat.Builder {
        val builtNotification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(subTitle)
            .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            .setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
            .setSmallIcon(R.drawable.load_drawable)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setWhen(System.currentTimeMillis())
            .setShowWhen(true)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setVibrate(longArrayOf(500, 500, 500, 500))
            .setAutoCancel(true)
        return builtNotification
    }

    fun createDefaultNotify() {
        val notifyManager = createNotificationManager()
        val buildNotify = createBuiltNotification()
        notifyManager.notify(Random.nextInt(), buildNotify.build())
    }

    fun createNotify(notifyManager: NotificationManager, buildNotify: NotificationCompat.Builder) {
        notifyManager.notify(Random.nextInt(), buildNotify.build())
    }
}