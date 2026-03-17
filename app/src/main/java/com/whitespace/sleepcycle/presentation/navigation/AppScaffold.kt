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
import com.whitespace.sleepcycle.presentation.components.BackdropBlurState
import com.whitespace.sleepcycle.presentation.components.backdropBlurSource

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppScaffold(navController: NavHostController) {

    val blurState = remember { BackdropBlurState() }

    Box(modifier = Modifier.fillMaxSize()) {

        // BACKDROP (what gets blurred)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .backdropBlurSource(blurState)
        ) {
            NavGraph(
                navController = navController,
                paddingValues = PaddingValues(bottom = 0.dp)
            )
        }

        // FOREGROUND (glass bar)
        FloatingBottomBar(
            navController = navController,
            blurState = blurState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp)
        )
    }
}