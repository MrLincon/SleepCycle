package com.whitespace.sleepcycle.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whitespace.sleepcycle.presentation.components.AppText

@Composable
fun SleepFullCardLayout(
    number: Int,
    chipText: String,
    cycle: String,
    sleepType: String,
    isPowerNap: Boolean = false,
    onClick: () -> Unit = {},
    ) {
    Box(
        modifier = Modifier
            .height(108.dp)
            .background(
                MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(size = 16.dp)
            )
            .border(
                1.dp,
                MaterialTheme.colorScheme.outline,
                RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(size = 16.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = if (isPowerNap) 24.dp else 24.dp)


    ) {
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NumberCardLayout(
                    number = number,
                    cardSize = 56,
                    textCardSize = 22,
                    textSize = 14,
                    cardBgColor = if (isPowerNap) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainer,
                    textCardBgColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    isPowerNapIcon = isPowerNap
                )

                Spacer(modifier = Modifier.width(16.dp))
                Column() {
                    AppText(
                        cycle,
                        fontSize = if (isPowerNap) 20.sp else 24.sp,
                        fontWeight = FontWeight.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    AppText(
                        if (isPowerNap) sleepType else "$sleepType ($chipText)",
                        fontSize = if (isPowerNap) 14.sp else 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = if (isPowerNap) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

            }
        }
    }
}