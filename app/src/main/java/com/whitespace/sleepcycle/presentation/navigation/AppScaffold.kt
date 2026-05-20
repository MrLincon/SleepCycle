package com.whitespace.sleepcycle.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.whitespace.sleepcycle.R
import io.github.mrlincon.glassybottombar.GlassyBottomBarScaffold
import io.github.mrlincon.glassybottombar.navigation.FloatingNavItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppScaffold(navController: NavHostController) {
    val items = remember {
        listOf(
            FloatingNavItem(Screen.Home.route, "Home", R.drawable.ic_home),
            FloatingNavItem(Screen.Alarm.route, "Alarm", R.drawable.ic_alarm),
            FloatingNavItem(Screen.Info.route, "Info", R.drawable.ic_info),
            FloatingNavItem(Screen.Settings.route, "Settings", R.drawable.ic_settings),
        )
    }

    GlassyBottomBarScaffold(
        navController = navController,
        items = items,
    ) {
        NavGraph(navController = navController)
    }
}
