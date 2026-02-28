package com.whitespace.sleepcycle.presentation.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.whitespace.sleepcycle.domain.NapDataModel
import com.whitespace.sleepcycle.presentation.components.ActiveAlarmsList
import com.whitespace.sleepcycle.presentation.components.AppText
import com.whitespace.sleepcycle.presentation.components.SetAlarmBottomSheet
import com.whitespace.sleepcycle.presentation.screens.home.components.HomeTopBar
import com.whitespace.sleepcycle.presentation.screens.home.components.SleepFullCardLayout
import com.whitespace.sleepcycle.presentation.screens.home.components.SleepFullRecommendedCardLayout
import com.whitespace.sleepcycle.presentation.screens.home.components.SleepHalfCardLayout
import com.whitespace.sleepcycle.utils.calculateWakeUpTime
import kotlinx.coroutines.delay
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showSheet by remember { mutableStateOf(false) }
    var selectedNap by remember { mutableStateOf<NapDataModel?>(null) }

    val dynamicWakeUpTime by produceState(
        initialValue = "",
        key1 = selectedNap,
        key2 = showSheet
    ) {
        if (showSheet && selectedNap != null) {
            while (true) {
                value = calculateWakeUpTime(selectedNap!!.durationMinutes, selectedNap!!.extraMin)
                delay(30000)
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            HomeTopBar()
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {

                ActiveAlarmsList(
                    alarms = state.activeAlarms,
                    onCancel = { alarm ->
                        viewModel.onEvent(HomeUiEvent.OnCancelAlarm(alarm))
                    }
                )

                AppText("Set Alarm", fontSize = 20.sp, fontWeight = FontWeight.Black)

                Spacer(Modifier.height(16.dp))

                SleepFullCardLayout(
                    number = 0,
                    chipText = "POWER NAP",
                    cycle = "Power Nap",
                    sleepType = "20 Min Booster",
                    isPowerNap = true,
                    onClick = {
                        selectedNap = NapDataModel(
                            durationMinutes = 1,
                            extraMin = 0,
                            label = "Power Nap - 20m",
                            cycles = "POWER NAP"
                        )
                        showSheet = true

                    }
                )

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SleepHalfCardLayout(
                        modifier = Modifier.weight(1f),
                        number = 1,
                        chipText = "1 CYCLE",
                        cycle = "1h 30m",
                        sleepType = "Survival Sleep",
                        onClick = {
                            selectedNap = NapDataModel(
                                durationMinutes = 90,
                                extraMin = 15,
                                label = "1 Cycle - 1h 30m",
                                cycles = "1 CYCLE"
                            )
                            showSheet = true
                        }
                    )
                    SleepHalfCardLayout(
                        modifier = Modifier.weight(1f),
                        number = 2,
                        chipText = "2 CYCLES",
                        cycle = "3h 00m",
                        sleepType = "Deep Sleep Peak",
                        onClick = {
                            selectedNap = NapDataModel(
                                durationMinutes = 180,
                                extraMin = 15,
                                label = "2 Cycles - 3h 00m",
                                cycles = "2 CYCLES"
                            )
                            showSheet = true
                        }
                    )
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SleepHalfCardLayout(
                        modifier = Modifier.weight(1f),
                        number = 3,
                        chipText = "3 CYCLES",
                        cycle = "4h 30m",
                        sleepType = "Memory Boost",
                        onClick = {
                            selectedNap = NapDataModel(
                                durationMinutes = 270,
                                extraMin = 15,
                                label = "3 Cycles - 4h 30m",
                                cycles = "3 CYCLES"
                            )
                            showSheet = true
                        }
                    )
                    SleepHalfCardLayout(
                        modifier = Modifier.weight(1f),
                        number = 4,
                        chipText = "4 CYCLES",
                        cycle = "6h 00m",
                        sleepType = "Functional Rest",
                        onClick = {
                            selectedNap = NapDataModel(
                                durationMinutes = 360,
                                extraMin = 15,
                                label = "4 Cycles - 6h 00m",
                                cycles = "4 CYCLES"
                            )
                            showSheet = true
                        }
                    )
                }

                Spacer(Modifier.height(16.dp))

                SleepFullRecommendedCardLayout(
                    number = 5,
                    chipText = "5 CYCLES",
                    cycle = "7h 30m",
                    sleepType = "Optimal for most adults",
                    onClick = {
                        selectedNap = NapDataModel(
                            durationMinutes = 450,
                            extraMin = 15,
                            label = "5 Cycles - 7h 30m",
                            cycles = "5 CYCLES"
                        )
                        showSheet = true
                    }
                )

                Spacer(Modifier.height(16.dp))

                SleepFullCardLayout(
                    number = 6,
                    chipText = "6 CYCLES",
                    cycle = "9h 00m",
                    sleepType = "Full Rest",
                    onClick = {
                        selectedNap = NapDataModel(
                            durationMinutes = 540,
                            extraMin = 15,
                            label = "6 Cycles - 9h 00m",
                            cycles = "6 CYCLES"
                        )
                        showSheet = true
                    }
                )

                Spacer(Modifier.height(16.dp))
            }


            if (showSheet && selectedNap != null) {
                SetAlarmBottomSheet(
                    title = selectedNap!!.cycles,
                    durationMinutes = selectedNap!!.durationMinutes,
                    wakeUpTime = dynamicWakeUpTime,
                    extraMinutesInfo = "Extra ${selectedNap!!.extraMin} mins will be added for falling asleep",
                    onStartClick = {
                        viewModel.onEvent(
                            HomeUiEvent.OnScheduleAlarm(
                                selectedNap!!.durationMinutes + selectedNap!!.extraMin,
                                selectedNap!!.label
                            )
                        )
                        showSheet = false
                        selectedNap = null
                    },
                    onDismiss = {
                        showSheet = false
                        selectedNap = null
                    },
                    sheetState = sheetState
                )
            }
        }
    }
}


