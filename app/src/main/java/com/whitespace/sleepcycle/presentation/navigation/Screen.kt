package com.whitespace.sleepcycle.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Alarm : Screen("alarm_screen")
    object Info : Screen("info_screen")
    object Settings : Screen("settings_screen")
}
