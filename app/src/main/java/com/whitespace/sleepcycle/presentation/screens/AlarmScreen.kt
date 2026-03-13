package com.whitespace.sleepcycle.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.whitespace.sleepcycle.presentation.components.ActiveAlarmsList
import com.whitespace.sleepcycle.presentation.components.AppText
import com.whitespace.sleepcycle.presentation.screens.home.HomeUiEvent
import com.whitespace.sleepcycle.presentation.screens.home.HomeViewModel

@Composable
fun AlarmScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Box(
                modifier = Modifier
                    .padding(top = 56.dp, start = 16.dp, end = 16.dp, bottom = 24.dp),
            ) {
                AppText(
                    text = "Active Alarms",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            if (state.activeAlarms.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    AppText(
                        text = "No alarms set",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            } else {
                ActiveAlarmsList(
                    alarms = state.activeAlarms,
                    showTitle = false,
                    onCancel = { alarm ->
                        viewModel.onEvent(HomeUiEvent.OnCancelAlarm(alarm))
                    }
                )
            }
        }
    }
}