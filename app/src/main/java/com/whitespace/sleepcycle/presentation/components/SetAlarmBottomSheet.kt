package com.whitespace.sleepcycle.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whitespace.sleepcycle.utils.formatDuration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetAlarmBottomSheet(
    title: String,
    durationMinutes: Int,
    wakeUpTime: String,
    extraMinutesInfo: String,
    onStartClick: () -> Unit,
    onDismiss: () -> Unit,
    sheetState: SheetState,
    modifier: Modifier = Modifier
) {

    val (mainText, unitText) = formatDuration(durationMinutes)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(width = 40.dp, height = 6.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.Gray.copy(0.5f))
            )
        }
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            // Title
            AppText(title, fontSize = 14.sp, fontWeight = FontWeight.Normal)

            Spacer(modifier = Modifier.height(24.dp))

            // Duration
            Row(
                verticalAlignment = Alignment.Bottom
            ) {

                AppText(mainText, fontSize = 72.sp, fontWeight = FontWeight.Black, textAlign = TextAlign.Center )

                if (unitText != null) {
                    Spacer(modifier = Modifier.width(8.dp))

                    AppText(unitText, fontSize = 72.sp, fontWeight = FontWeight.Black, textAlign = TextAlign.Center )

                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Wake time row
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))

                AppText("Wake up at $wakeUpTime", fontSize = 14.sp, fontWeight = FontWeight.SemiBold )

            }

            Spacer(modifier = Modifier.height(12.dp))

            // Extra info
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))

                AppText(extraMinutesInfo, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurfaceVariant )

            }

            Spacer(modifier = Modifier.height(32.dp))

            // Gradient Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(

                                MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                            )
                        )
                    )
                    .clickable { onStartClick() },
                contentAlignment = Alignment.Center
            ) {
                AppText("Set Alarm", fontSize = 18.sp, fontWeight = FontWeight.SemiBold )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}