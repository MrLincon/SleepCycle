package com.whitespace.sleepcycle.presentation.screens.info

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.whitespace.sleepcycle.presentation.components.AppTopBar
import com.whitespace.sleepcycle.presentation.screens.info.components.InfoSectionBlock
import com.whitespace.sleepcycle.presentation.screens.info.components.SleepCycleChart

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { AppTopBar(title = "Sleep Info") }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 96.dp)
        ) {

            item {
                SleepCycleChart(
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(Modifier.height(12.dp))
            }

            infoSections.forEachIndexed { index, section ->
                item {
                    InfoSectionBlock(
                        section = section,
                        sectionIndex = index,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }
}