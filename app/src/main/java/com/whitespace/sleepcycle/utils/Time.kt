package com.whitespace.sleepcycle.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
fun calculateWakeUpTime(
    napTime: Int,
    sleepTime: Int
): String {
    val totalMinutes = napTime + sleepTime

    val wakeUpTime = LocalTime.now()
        .plus(totalMinutes.toLong(), ChronoUnit.MINUTES)

    val formatter = DateTimeFormatter.ofPattern("hh:mm a")

    return wakeUpTime.format(formatter)
}

fun formatDuration(totalMinutes: Int): Pair<String, String?> {
    return if (totalMinutes < 60) {
        totalMinutes.toString() to "min"
    } else {
        val hours = totalMinutes / 60
        val minutes = totalMinutes % 60

        val mainText = if (minutes == 0) {
            "$hours hr\n00 min"
        } else {
            "$hours hr \n$minutes min"
        }

        mainText to null
    }
}