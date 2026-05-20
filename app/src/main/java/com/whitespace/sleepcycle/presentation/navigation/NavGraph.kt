package com.whitespace.sleepcycle.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.whitespace.sleepcycle.presentation.screens.AlarmScreen
import com.whitespace.sleepcycle.presentation.screens.info.InfoScreen
import com.whitespace.sleepcycle.presentation.screens.home.HomeScreen
import com.whitespace.sleepcycle.presentation.screens.settings.SettingsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) { HomeScreen(navController = navController) }
        composable(route = Screen.Alarm.route) { AlarmScreen(navController = navController) }
        composable(route = Screen.Info.route) { InfoScreen(navController = navController) }
        composable(route = Screen.Settings.route) { SettingsScreen(navController = navController) }
    }
}
