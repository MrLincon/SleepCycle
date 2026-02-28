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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whitespace.sleepcycle.presentation.components.AppText

@Composable
fun SleepFullRecommendedCardLayout(
    number: Int,
    chipText: String,
    cycle: String,
    sleepType: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .height(108.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
                    )
                ),
                shape = RoundedCornerShape(size = 16.dp)
            )
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5F),
                RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(size = 16.dp))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 24.dp)
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
                    cardBgColor = MaterialTheme.colorScheme.primary,
                    textCardBgColor = MaterialTheme.colorScheme.onPrimary,
                    textColor = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(16.dp))
                Column() {
                    Row() {
                        AppText(cycle, fontSize = 24.sp, fontWeight = FontWeight.Black)
                        Spacer(modifier = Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1F),
                                    shape = RoundedCornerShape(100)
                                )
                                .border(width = 1.dp, color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5F),shape = RoundedCornerShape(100))
                                .padding(vertical = 2.dp, horizontal = 12.dp)

                        ) {
                            Text(
                                "RECOMMENDED",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    AppText(
                        "$chipText  •  $sleepType",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

            }


        }
    }
}