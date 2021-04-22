package com.denis.notes.screens.note

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.denis.notes.R
import com.denis.notes.utilits.APP_ACTIVITY
import com.denis.notes.utilits.CHANNEL_ID
import com.denis.notes.utilits.NOTIFY_ID

class Receiver:BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {


        val builder = NotificationCompat.Builder(context!!, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_access_time)
            .setContentTitle("Напоминание")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Кажется, вам нужно что-то сделать"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)



        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFY_ID, builder.build())
    }
}