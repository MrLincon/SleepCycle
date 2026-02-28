package com.whitespace.sleepcycle.alarm.scheduler



import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.whitespace.sleepcycle.MainActivity
import com.whitespace.sleepcycle.alarm.receiver.AlarmReceiver
import com.whitespace.sleepcycle.alarm.service.AlarmNotificationService
import com.whitespace.sleepcycle.data.dao.AlarmDao
import com.whitespace.sleepcycle.data.entity.AlarmEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AlarmSchedulerImpl @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val alarmDao: AlarmDao
) : AlarmScheduler {

    private val alarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override suspend fun schedule(timeInMillis: Long, label: String): Int {
        // Save to DB
        val id = alarmDao.insert(
            AlarmEntity(triggerTimeMillis = timeInMillis, label = label)
        ).toInt()

        // Schedule alarm using the DB id as request code (unique per alarm)
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("alarm_id", id)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context, id, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val showIntent = Intent(context, MainActivity::class.java)
        val showPendingIntent = PendingIntent.getActivity(
            context, id + 10000, showIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmClockInfo = AlarmManager.AlarmClockInfo(timeInMillis, showPendingIntent)
        alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)

        // Show notification
        val notifIntent = Intent(context, AlarmNotificationService::class.java).apply {
            putExtra(AlarmNotificationService.EXTRA_TRIGGER_TIME, timeInMillis)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(notifIntent)
        } else {
            context.startService(notifIntent)
        }

        return id
    }

    override suspend fun cancel(alarmId: Int) {
        // Cancel alarm
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, alarmId, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)

        // Remove from DB
        alarmDao.deleteById(alarmId)

        // Stop notification
        context.stopService(Intent(context, AlarmNotificationService::class.java))
    }
}