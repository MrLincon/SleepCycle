package com.whitespace.sleepcycle.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whitespace.sleepcycle.R
import com.whitespace.sleepcycle.presentation.components.AppText

@Composable
fun NumberCardLayout(
    number: Int, cardSize: Int = 40, textCardSize: Int = 18, textSize: Int = 12,
    cardBgColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    textCardBgColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    isPowerNapIcon: Boolean = false,
) {
    Box(
        modifier = Modifier
            .background(
                color = cardBgColor,
                shape = RoundedCornerShape(percent = 100)
            )
            .size(cardSize.dp)
            .shadow(
                elevation = if (isPowerNapIcon) 20.dp else 0.dp,
                shape = CircleShape,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary
            )


    ) {
        if (isPowerNapIcon)

            Icon(
                painter = painterResource(id = R.drawable.ic_flash),
                contentDescription = "Power Nap",
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.Center) // This centers the SVG inside the parent Box
            )
        else Box(
            modifier = Modifier
                .background(
                    color = textCardBgColor,
                    shape = RoundedCornerShape(size = 4.dp)
                )

                .size(textCardSize.dp)
                .align(alignment = Alignment.Center),
            contentAlignment = Alignment.Center

        ) {
            AppText(
                text = "$number",
                fontSize = textSize.sp,
                fontWeight = FontWeight.Black,
                color = textColor
            )
        }
    }
}

