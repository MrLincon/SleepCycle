package com.whitespace.sleepcycle.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whitespace.sleepcycle.presentation.components.AppText

@Composable
fun ChipTextLayout(
    text: String,
    bgColor: Color = MaterialTheme.colorScheme.surfaceContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Box(
        modifier = Modifier
            .background(
                bgColor,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        AppText(text = text, color = textColor, fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}