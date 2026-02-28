package com.whitespace.sleepcycle.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whitespace.sleepcycle.R
import com.whitespace.sleepcycle.data.entity.AlarmEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ActiveAlarmsList(
    alarms: List<AlarmEntity>,
    onCancel: (AlarmEntity) -> Unit
) {
    if (alarms.isEmpty()) return

    Column(modifier = Modifier.fillMaxWidth()) {

        AppText("Active Alarms", fontSize = 20.sp, fontWeight = FontWeight.Black)

        Spacer(Modifier.height(16.dp))

        alarms.forEach { alarm ->
            val timeFormatted = SimpleDateFormat("hh:mm a", Locale.getDefault())
                .format(Date(alarm.triggerTimeMillis))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row() {
                        Card(
                            modifier = Modifier
                                .size(56.dp)
                                .shadow(
                                    elevation = 20.dp,
                                    shape = CircleShape,
                                    ambientColor = MaterialTheme.colorScheme.secondary,
                                    spotColor = MaterialTheme.colorScheme.secondary
                                )
                                .clip(RoundedCornerShape(100)),

                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_alarm_clock),
                                    contentDescription = "Clock",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {

                            AppText(
                                timeFormatted,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )

                            Spacer(Modifier.height(4.dp))

                            AppText(
                                alarm.label,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                        }
                    }

                    Button(
                        onClick = { onCancel(alarm) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error.copy(
                                alpha = 0.1f
                            )
                        )
                    ) {
                        AppText(
                            "Cancel",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.error
                        )

                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}