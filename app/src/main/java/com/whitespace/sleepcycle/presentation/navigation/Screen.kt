package com.whitespace.sleepcycle.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Stats : Screen("stats_screen")
    object Settings : Screen("settings_screen")
}
