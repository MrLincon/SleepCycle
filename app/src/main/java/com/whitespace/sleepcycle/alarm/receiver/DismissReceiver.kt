package com.whitespace.sleepcycle.alarm.receiver
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.whitespace.sleepcycle.alarm.service.AlarmService

class DismissReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        context.stopService(Intent(context, AlarmService::class.java))
    }
}