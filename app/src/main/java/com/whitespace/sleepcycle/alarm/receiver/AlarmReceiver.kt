package com.whitespace.sleepcycle.alarm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import com.whitespace.sleepcycle.alarm.service.AlarmNotificationService
import com.whitespace.sleepcycle.alarm.service.AlarmService
import com.whitespace.sleepcycle.data.dao.AlarmDao
import com.whitespace.sleepcycle.data.database.AlarmDatabase
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var alarmDao: AlarmDao

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("AlarmReceiver", "Alarm received!")

        // Stop the "Alarm set for..." notification
        context.stopService(Intent(context, AlarmNotificationService::class.java))

        // Delete from DB using the SAME dao instance Room Flow observes
        val alarmId = intent?.getIntExtra("alarm_id", -1) ?: -1
        if (alarmId != -1) {
            val pendingResult = goAsync()
            CoroutineScope(Dispatchers.IO).launch {
                alarmDao.deleteById(alarmId)
                pendingResult.finish()
            }
        }

        // Start the alarm sound + wake up screen
        val serviceIntent = Intent(context, AlarmService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }
}