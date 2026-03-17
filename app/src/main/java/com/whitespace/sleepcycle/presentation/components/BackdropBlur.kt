package com.whitespace.sleepcycle.presentation.components

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Shader
import android.os.Build
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.IntSize
import kotlin.random.Random

class BackdropBlurState {
    internal var graphicsLayer: GraphicsLayer? = null
    internal var sourcePositionInRoot: Offset = Offset.Zero
    internal var childPositionInRoot: Offset = Offset.Zero
}

private fun generateNoiseBitmap(size: Int = 128): Bitmap {
    val pixels = IntArray(size * size) {
        val gray = 128 + Random.nextInt(-12, 12) // tight range around mid-gray
        android.graphics.Color.rgb(gray, gray, gray)
    }
    return Bitmap.createBitmap(pixels, size, size, Bitmap.Config.ARGB_8888)
}

fun Modifier.backdropBlurSource(state: BackdropBlurState): Modifier {
    return this
        .onGloballyPositioned {
            state.sourcePositionInRoot = it.positionInRoot()
        }
        .drawWithCache {
            val layer = obtainGraphicsLayer()
            onDrawWithContent {
                layer.record {
                    this@onDrawWithContent.drawContent()
                }
                state.graphicsLayer = layer
                drawLayer(layer)
            }
        }
}

fun Modifier.backdropBlurChild(
    state: BackdropBlurState,
    blurRadius: Float = 20f,
    noiseFactor: Float = 0f,
    tintColor: Color = Color.Transparent
): Modifier {
    return this
        .onGloballyPositioned {
            state.childPositionInRoot = it.positionInRoot()
        }
        .drawWithCache {
            val childLayer = obtainGraphicsLayer()

            val noisePaint = if (noiseFactor > 0f) {
                val noiseBitmap = generateNoiseBitmap(128)
                val shader = BitmapShader(
                    noiseBitmap,
                    Shader.TileMode.REPEAT,
                    Shader.TileMode.REPEAT
                )
                Paint().also { paint ->
                    paint.asFrameworkPaint().apply {
                        isAntiAlias = true
                        setShader(shader)
                        alpha = (noiseFactor.coerceIn(0f, 1f) * 255).toInt()
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            blendMode = android.graphics.BlendMode.OVERLAY
                        }
                    }
                }
            } else null

            onDrawWithContent {
                val sourceLayer = state.graphicsLayer
                if (sourceLayer != null) {
                    val offset = state.childPositionInRoot - state.sourcePositionInRoot

                    childLayer.record(
                        size = IntSize(size.width.toInt(), size.height.toInt())
                    ) {
                        translate(-offset.x, -offset.y) {
                            drawLayer(sourceLayer)
                        }
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        childLayer.renderEffect = android.graphics.RenderEffect
                            .createBlurEffect(
                                blurRadius, blurRadius,
                                android.graphics.Shader.TileMode.CLAMP
                            )
                            .asComposeRenderEffect()
                    }

                    // Blurred background
                    drawLayer(childLayer)

                    // Optional tint
                    if (tintColor != Color.Transparent) {
                        drawRect(color = tintColor)
                    }

                    // Noise grain
                    if (noiseFactor > 0f && noisePaint != null) {
                        drawIntoCanvas { canvas ->
                            canvas.drawRect(
                                left = 0f,
                                top = 0f,
                                right = size.width,
                                bottom = size.height,
                                paint = noisePaint
                            )
                        }
                    }
                }

                drawContent()
            }
        }
}