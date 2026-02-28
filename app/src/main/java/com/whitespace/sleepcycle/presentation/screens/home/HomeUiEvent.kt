package com.whitespace.sleepcycle.presentation.screens.home

import com.whitespace.sleepcycle.data.entity.AlarmEntity

sealed class HomeUiEvent {
    data class OnScheduleAlarm(val durationMinutes: Int, val label: String) : HomeUiEvent()
    data class OnCancelAlarm(val alarm: AlarmEntity) : HomeUiEvent()
}