package com.whitespace.sleepcycle.presentation.screens.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whitespace.sleepcycle.alarm.scheduler.AlarmScheduler
import com.whitespace.sleepcycle.data.dao.AlarmDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val scheduler: AlarmScheduler,
    private val alarmDao: AlarmDao
) : ViewModel() {

    init {
        // Clean up any expired alarms on launch
        viewModelScope.launch { alarmDao.clearExpired() }
    }

    val state = alarmDao.getActiveAlarms(System.currentTimeMillis())
        .map { alarms -> HomeUiState(activeAlarms = alarms) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeUiState())

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnScheduleAlarm -> {
                viewModelScope.launch {
                    val triggerTime = System.currentTimeMillis() + event.durationMinutes * 60 * 1000L
                    scheduler.schedule(triggerTime, event.label)
                }
            }
            is HomeUiEvent.OnCancelAlarm -> {
                viewModelScope.launch {
                    scheduler.cancel(event.alarm.id)
                }
            }
        }
    }
}