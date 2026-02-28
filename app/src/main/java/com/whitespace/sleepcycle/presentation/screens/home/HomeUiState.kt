package com.whitespace.sleepcycle.presentation.screens.home

import com.whitespace.sleepcycle.data.entity.AlarmEntity


data class HomeUiState(
    val activeAlarms: List<AlarmEntity> = emptyList()
)