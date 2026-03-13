package com.whitespace.sleepcycle.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppScaffold(navController: NavHostController) {
    val hazeState = remember { HazeState() }

    Box(modifier = Modifier.fillMaxSize()) {

        // BACKDROP (what gets blurred)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(hazeState)
        ) {
            NavGraph(
                navController = navController,
                paddingValues = PaddingValues(bottom = 0.dp)
            )
        }

        // FOREGROUND (glass bar)
        FloatingBottomBar(
            navController = navController,
            hazeState = hazeState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp)
        )
    }
}