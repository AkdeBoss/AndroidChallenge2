package com.example.androiddevchallenge.ui.scenes

import android.graphics.Color
import android.graphics.Typeface
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DropText(modifier: Modifier = Modifier,text:String="00",start_anim:Boolean=false) {

    val darkTheme: Boolean = isSystemInDarkTheme()
    val colors =
        listOf(androidx.compose.ui.graphics.Color.Red, androidx.compose.ui.graphics.Color.Black)
    var critical by remember { mutableStateOf(colors[0]) }

    val paint = remember {
        Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            color = if (darkTheme) {
                Color.WHITE
            } else {
                Color.BLACK
            }

        }
    }
    val infiniteTransition = rememberInfiniteTransition()
    val bounds = remember { android.graphics.Rect() }
    val animatedOffset = remember {
        Animatable(Offset(0f, 0f), Offset.VectorConverter)
    }
        val top by infiniteTransition.animateFloat(
            initialValue = 10F, targetValue = 20F, animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )

        Canvas(modifier = modifier, onDraw = {
            val centerOffset = Offset(this.center.x / 2, this.center.y)

            drawIntoCanvas { canvas ->
                val textSize = 60.sp.toPx()
                paint.apply { this.textSize = textSize }
                paint.getTextBounds(text, 0, text.length, bounds)
                val x = centerOffset.x + (textSize / 2)
                val y = if (start_anim) {
                    centerOffset.y + top + (textSize / 2)
                } else centerOffset.y + (textSize / 2)
                canvas.nativeCanvas.drawText(text, x, y, paint)
            }


        })

}


@ExperimentalAnimationApi
@Preview("DropText Theme", widthDp = 360, heightDp = 640)
@Composable
fun DropTextPreview() {
    MyTheme(darkTheme = true) {
        DropText()
    }
}