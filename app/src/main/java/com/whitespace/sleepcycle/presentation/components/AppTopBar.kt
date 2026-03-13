package com.whitespace.sleepcycle.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppTopBar(title: String) {
    Box(
        modifier = Modifier.padding(top = 56.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
    ) {
        AppText(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Black
        )
    }
}