package com.whitespace.sleepcycle.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.whitespace.sleepcycle.ui.theme.SpaceGrotesk


@Composable
fun AppText(
    text: String,

    modifier: Modifier = Modifier,

    color: Color = MaterialTheme.colorScheme.onSurface,

    fontSize: TextUnit = 14.sp,

    fontWeight: FontWeight = FontWeight.Normal,

    fontFamily: FontFamily = SpaceGrotesk,

    textAlign: TextAlign? = null,

    maxLines: Int = Int.MAX_VALUE,

    overflow: TextOverflow = TextOverflow.Clip,

    lineHeight: TextUnit = TextUnit.Unspecified,

    letterSpacing: TextUnit = TextUnit.Unspecified,

    style: TextStyle = TextStyle.Default,

    ) {

    Text(

        text = text,

        modifier = modifier,

        color = color,

        fontSize = fontSize,

        fontWeight = fontWeight,

        fontFamily = fontFamily,

        textAlign = textAlign,

        maxLines = maxLines,

        overflow = overflow,

        lineHeight = lineHeight,

        letterSpacing = letterSpacing,

        style = style.merge(
            TextStyle(
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            )
        )

    )

}
