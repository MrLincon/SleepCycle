package com.whitespace.sleepcycle.presentation.screens.info.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whitespace.sleepcycle.domain.InfoSectionModel
import com.whitespace.sleepcycle.presentation.components.AppText

@Composable
fun InfoSectionBlock(
    section: InfoSectionModel,
    sectionIndex: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
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
            // Section header
            Row(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceBright,
                    )
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    AppText(
                        text = "0${sectionIndex + 1}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Spacer(Modifier.width(14.dp))
                Column {
                    AppText(
                        text = section.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                    )
                    Spacer(Modifier.height(4.dp))
                    AppText(
                        text = section.subtitle,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            HorizontalDivider(
                color = MaterialTheme.colorScheme.outline,
                thickness = 1.dp
            )

            section.items.forEachIndexed { index, card ->
                ExpandableInfoCard(card = card)
                if (index != section.items.lastIndex) {
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