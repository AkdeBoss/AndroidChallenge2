/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.models.CountDownModel
import com.example.androiddevchallenge.ui.theme.*
import com.example.androiddevchallenge.viewmodels.CountDownViewModel
import kotlin.math.PI
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import com.example.androiddevchallenge.ui.scenes.*
import kotlinx.coroutines.launch
import java.time.Clock


@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    private val viewModel: CountDownViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(viewModel)
            }
        }
    }
}


// Start building your app here!
@ExperimentalAnimationApi
@Composable
fun MyApp(viewModel: CountDownViewModel? = null) {

    val isTimerSet: Boolean by viewModel!!.isTimerSet.observeAsState(false)

    AnimatedVisibility(visible = isTimerSet) {  ActionScene(modifier=Modifier.fillMaxHeight().fillMaxWidth(),viewModel)}
    AnimatedVisibility(visible = !isTimerSet) { OptionsGrid(modifier=Modifier.fillMaxHeight().fillMaxWidth(),viewModel) }

    // critical= if(countDown.isCritical()) {colors[(countDown.seconds%2)]}else colors[1]
   //
}



@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
