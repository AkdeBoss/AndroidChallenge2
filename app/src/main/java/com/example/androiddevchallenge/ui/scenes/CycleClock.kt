package com.example.androiddevchallenge.ui.scenes

import android.graphics.Color
import android.graphics.Typeface
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.models.CountDownModel
import com.example.androiddevchallenge.ui.theme.*
import kotlinx.coroutines.launch
import kotlin.math.PI


@Composable
fun RotatingDial(modifier: Modifier = Modifier,seconds: Long=0) {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val scope = rememberCoroutineScope()

    Canvas(modifier = modifier, onDraw = {
        val centerOffset=Offset(this.center.x/4,this.center.y)
        val innerDials = this.size.width
        val outerDials = innerDials - 1.dp.toPx()

        val innerWidth = 4.dp.toPx()
        val outerWidth = 12.dp.toPx()

        val innerDial = 1.dp.toPx()
        val outerDial = 2.dp.toPx()

        val smallInterval =  (2 * PI.toFloat() * innerDials - 60 * innerDial) / 60
        val bigInterval = (2 * PI.toFloat() * outerDials - 12 * outerDial) / 12

        drawLine(
            if(darkTheme){
                white}else{
                black}, centerOffset,
            Offset(outerDials+outerWidth, this.center.y)
        )
        rotate(seconds.secondsToDegree(), centerOffset) {

            drawCircle(
                color = if(darkTheme){
                    white}else{
                    black},
                radius = innerDials,
                style = Stroke(
                    width = innerWidth,
                    pathEffect = PathEffect.dashPathEffect(
                        floatArrayOf(
                            innerDial,
                            smallInterval
                        )
                    )
                ), center =centerOffset
            )

            drawCircle(
                color = if(darkTheme){
                    white}else{
                    black},
                radius = outerDials,
                style = Stroke(
                    width = outerWidth,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(outerDial, bigInterval))
                ),center =centerOffset
            )


        }

    })



}


private fun Long.secondsToDegree() = (((if(this>=60){this % 60}else{this}).toInt()  * 6)).toFloat()

@ExperimentalAnimationApi
@Preview("DialPreview Theme", widthDp = 360, heightDp = 640)
@Composable
fun DialPreview() {
    MyTheme(darkTheme = true) {
        RotatingDial()
    }
}