package com.whitespace.sleepcycle.presentation.screens.home.viewmodel

import com.whitespace.sleepcycle.data.entity.AlarmEntity


data class HomeUiState(
    val activeAlarms: List<AlarmEntity> = emptyList()
)