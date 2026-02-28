package com.whitespace.sleepcycle.alarm.scheduler


interface AlarmScheduler {
    suspend fun schedule(timeInMillis: Long, label: String): Int
    suspend fun cancel(alarmId: Int)
}