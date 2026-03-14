package com.whitespace.sleepcycle.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.whitespace.sleepcycle.presentation.components.AppText

@Composable
fun FloatingBottomBarItem(
    selected: Boolean,
    label: String,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit
) {

    val containerColor by animateColorAsState(
        targetValue = if (selected)
            MaterialTheme.colorScheme.primary
        else
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        animationSpec = tween(durationMillis = 300, easing = EaseInOutCubic),
        label = "containerColor"
    )

    val iconTint by animateColorAsState(
        targetValue = if (selected)
            MaterialTheme.colorScheme.onPrimary
        else
            MaterialTheme.colorScheme.onSurface,
        animationSpec = tween(durationMillis = 300, easing = EaseInOutCubic),
        label = "iconTint"
    )

    val cornerRadius by animateDpAsState(
        targetValue = 100.dp,
        animationSpec = tween(durationMillis = 300, easing = EaseInOutCubic),
        label = "cornerRadius"
    )

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .clip(shape = RoundedCornerShape(cornerRadius))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(cornerRadius),
        color = containerColor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                tint = iconTint
            )
            AnimatedVisibility(
                visible = selected,
                enter = expandHorizontally(
                    animationSpec = tween(300, easing = EaseInOutCubic)
                ) + fadeIn(tween(300)),
                exit = shrinkHorizontally(
                    animationSpec = tween(300, easing = EaseInOutCubic)
                ) + fadeOut(tween(150))
            ) {
                Row {
                    Spacer(Modifier.width(8.dp))
                    AppText(
                        text = label,
                        color = iconTint, fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}