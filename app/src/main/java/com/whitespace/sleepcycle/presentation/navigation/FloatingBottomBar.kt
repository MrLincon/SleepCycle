package com.whitespace.sleepcycle.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.whitespace.sleepcycle.R
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect

private val GlassShape = RoundedCornerShape(60.dp)

@Composable
fun FloatingBottomBar(
    navController: NavHostController,
    hazeState: HazeState,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        FloatingNavItem(Screen.Home.route, "Home", R.drawable.ic_home),
        FloatingNavItem(Screen.Alarm.route, "Alarm", R.drawable.ic_alarm),
        FloatingNavItem(Screen.Info.route, "Info", R.drawable.ic_info),
        FloatingNavItem(Screen.Settings.route, "Settings", R.drawable.ic_settings),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = modifier
            .wrapContentWidth()
            .height(66.dp)
            .clip(GlassShape)
            .hazeEffect(
                state = hazeState,
                style = HazeDefaults.style(
                    backgroundColor = Color.Black.copy(alpha = 0.22f),
                    blurRadius = 20.dp,
                    noiseFactor = 0.12f,
                )
            )
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.35f),
                        Color.White.copy(alpha = 0.08f)
                    )
                ),
                shape = GlassShape
            )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items.forEach { item ->
                val selected = currentRoute == item.route
                FloatingBottomBarItem(
                    selected = selected,
                    label = item.label,
                    iconRes = item.iconRes,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(Screen.Home.route) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

data class FloatingNavItem(
    val route: String,
    val label: String,
    @param:DrawableRes val iconRes: Int
)