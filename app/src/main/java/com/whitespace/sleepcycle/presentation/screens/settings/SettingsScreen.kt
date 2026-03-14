package com.whitespace.sleepcycle.presentation.screens.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.whitespace.sleepcycle.presentation.components.AppText
import com.whitespace.sleepcycle.presentation.components.AppTopBar
import androidx.core.net.toUri
import com.whitespace.sleepcycle.R
import com.whitespace.sleepcycle.presentation.screens.settings.components.SettingsRow

private const val APP_PACKAGE = "com.whitespace.sleepcycle"
private const val FEEDBACK_EMAIL = "ahamedlincon.office@gmail.com"
private const val PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=$APP_PACKAGE"
private const val SHARE_MESSAGE = "Check out Sleep Cycle to optimise sleep with science-backed cycles. Check it out! \n\n$PLAY_STORE_URL"

data class SettingsItem(
    val title: String,
    val subtitle: String,
    @DrawableRes val iconRes: Int,
    val onClick: (Context) -> Unit
)

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current

    val settingsItems = listOf(
        SettingsItem(
            title = "Rate the App",
            subtitle = "Enjoying Sleep Cycle? Leave us a review",
            iconRes = R.drawable.ic_rate,
            onClick = { ctx ->
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = PLAY_STORE_URL.toUri()
                    setPackage("com.android.vending")
                }
                try {
                    ctx.startActivity(intent)
                } catch (e: Exception) {
                    ctx.startActivity(Intent(Intent.ACTION_VIEW, PLAY_STORE_URL.toUri()))
                }
            }
        ),
        SettingsItem(
            title = "Share",
            subtitle = "Share Sleep Cycle with friends and family",
            iconRes = R.drawable.ic_share,
            onClick = { ctx ->
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, SHARE_MESSAGE)
                }
                ctx.startActivity(Intent.createChooser(intent, "Share Sleep Cycle"))
            }
        ),
        SettingsItem(
            title = "Send Feedback",
            subtitle = "Report a bug or suggest a feature",
            iconRes = R.drawable.ic_feedback,
            onClick = { ctx ->
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = "mailto:$FEEDBACK_EMAIL".toUri()
                    putExtra(Intent.EXTRA_SUBJECT, "Sleep Cycle App Feedback")
                }
                ctx.startActivity(Intent.createChooser(intent, "Send Feedback"))
            }
        ),
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { AppTopBar(title = "Settings") }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Column {
                    settingsItems.forEachIndexed { index, item ->
                        SettingsRow(
                            item = item,
                            onClick = { item.onClick(context) }
                        )
                        if (index != settingsItems.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                color = MaterialTheme.colorScheme.outline,
                                thickness = 1.dp
                            )
                        }
                    }
                }
            }
        }
    }
}

