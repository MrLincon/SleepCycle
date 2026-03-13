package com.whitespace.sleepcycle.presentation.screens.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
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

private const val APP_PACKAGE = "com.whitespace.sleepcycle"
private const val FEEDBACK_EMAIL = "ahamedlincon.office@gmail.com"
private const val PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=$APP_PACKAGE"
private const val SHARE_MESSAGE = "Check out Sleep Cycle to optimise sleep with science-backed cycles. Check it out! \n\n$PLAY_STORE_URL"

private data class SettingsItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val onClick: (Context) -> Unit
)

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current

    val settingsItems = listOf(
        SettingsItem(
            title = "Rate the App",
            subtitle = "Enjoying Sleep Cycle? Leave us a review",
            icon = Icons.Filled.Star,
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
            icon = Icons.Filled.Share,
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
            icon = Icons.Filled.Email,
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
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                                thickness = 1.dp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsRow(
    item: SettingsItem,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(Modifier.width(14.dp))
            Column {
                AppText(
                    text = item.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(Modifier.height(4.dp))
                AppText(
                    text = item.subtitle,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp)
        )
    }
}