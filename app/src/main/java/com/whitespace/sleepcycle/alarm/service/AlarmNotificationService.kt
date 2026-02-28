package com.whitespace.sleepcycle.alarm.service

import android.app.*
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.whitespace.sleepcycle.MainActivity
import com.whitespace.sleepcycle.R
import com.whitespace.sleepcycle.alarm.receiver.CancelAlarmReceiver
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AlarmNotificationService : Service() {

    companion object {
        const val EXTRA_TRIGGER_TIME = "extra_trigger_time"
        const val CHANNEL_ID = "alarm_notification_channel"
        const val NOTIFICATION_ID = 2
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val triggerTime = intent?.getLongExtra(EXTRA_TRIGGER_TIME, 0L) ?: 0L
        val remaining = triggerTime - System.currentTimeMillis()

        if (remaining <= 0) {
            stopSelf()
            return START_NOT_STICKY
        }

        createNotificationChannel()

        val alarmTimeFormatted = SimpleDateFormat("hh:mm a", Locale.getDefault())
            .format(Date(triggerTime))

        val totalMinutes = remaining / 1000 / 60
        val hours = totalMinutes / 60
        val minutes = totalMinutes % 60
        val durationText = if (hours > 0) "$hours hr $minutes min from now"
        else "${minutes+1} min from now"

        // Open app on tap
        val openAppIntent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        val openAppPendingIntent = PendingIntent.getActivity(
            this, 0, openAppIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )


        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Alarm set for $alarmTimeFormatted")
            .setContentText(durationText)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .setAutoCancel(false)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setContentIntent(openAppPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .build()

        startForeground(NOTIFICATION_ID, notification)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Alarm Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                setShowBadge(false)
                setSound(null, null)
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}