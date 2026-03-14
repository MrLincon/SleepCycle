package com.whitespace.sleepcycle.core.alarm.service

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.whitespace.sleepcycle.R
import com.whitespace.sleepcycle.AlarmActivity
import com.whitespace.sleepcycle.core.alarm.receiver.DismissReceiver

class AlarmService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    @SuppressLint("FullScreenIntentPolicy")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        createNotificationChannel()

        // Full-screen intent to launch AlarmActivity
        val fullScreenIntent = Intent(this, AlarmActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        val fullScreenPendingIntent = PendingIntent.getActivity(
            this,
            1003,
            fullScreenIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Stop alarm when notification is swiped away
        val dismissIntent = Intent(this, DismissReceiver::class.java)
        val dismissPendingIntent = PendingIntent.getBroadcast(
            this,
            1006,
            dismissIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, "alarm_channel")
            .setContentTitle("⏰ Alarm")
            .setContentText("Wake up!")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setOngoing(false)
            .setDeleteIntent(dismissPendingIntent)
            .addAction(0, "Dismiss", dismissPendingIntent)
            .setFullScreenIntent(fullScreenPendingIntent, true)
            .build()

        startForeground(1, notification)

        // Play default alarm sound
        val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
            ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                android.media.AudioAttributes.Builder()
                    .setUsage(android.media.AudioAttributes.USAGE_ALARM)
                    .setContentType(android.media.AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setLegacyStreamType(android.media.AudioManager.STREAM_ALARM)
                    .build()
            )
            setDataSource(this@AlarmService, alarmUri)
            isLooping = true
            prepare()
            start()
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "alarm_channel",
                "Alarm Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setSound(
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM),
                    android.media.AudioAttributes.Builder()
                        .setUsage(android.media.AudioAttributes.USAGE_ALARM)
                        .setContentType(android.media.AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}