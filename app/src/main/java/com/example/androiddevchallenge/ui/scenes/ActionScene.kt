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
package com.example.androiddevchallenge.ui.scenes

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewmodels.CountDownViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ActionScene(modifier: Modifier = Modifier, viewModel: CountDownViewModel? = null) {
    val countDown: Long by viewModel!!.countDown.observeAsState(0)
    val isTicking: Boolean by viewModel!!.isTicking.observeAsState(false)

    Column(modifier = Modifier.padding(16.dp).fillMaxWidth().fillMaxHeight()) {
        Spacer(modifier = Modifier.weight(1.0f, true).height(20.dp))
        ClockGrid(modifier = Modifier.weight(1.0f, true).padding(16.dp).fillMaxWidth(), countDown = countDown, isTicking = isTicking)
        Spacer(modifier = Modifier.weight(1.0f, true).height(20.dp))

        androidx.compose.animation.AnimatedVisibility(visible = isTicking) {
            Button(
                modifier = Modifier.weight(1.0f, false).padding(12.dp).fillMaxWidth(),
                onClick = {
                    viewModel!!.stopTicking()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text(text = "Stop it!!", color = Color.White)
            }
        }
        androidx.compose.animation.AnimatedVisibility(visible = !isTicking) {
            Button(
                modifier = Modifier.weight(1.0f, false).padding(12.dp).fillMaxWidth(),
                onClick = {
                    viewModel!!.stopSession()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text(text = "Close", color = Color.White)
            }
        }
    }
}

@Composable
fun ClockGrid(modifier: Modifier = Modifier, countDown: Long = 0, isTicking: Boolean = false) {
    Row(modifier = modifier.fillMaxWidth()) {
        RotatingDial(
            modifier = Modifier
                .weight(1.0f, true)
                .fillMaxWidth()
                .fillMaxHeight(),
            countDown
        )
        DropText(
            modifier = Modifier
                .weight(1.0f, true).fillMaxWidth().fillMaxHeight(),
            text = "%03d".format(countDown),
            isTicking
        )
    }
}

@ExperimentalAnimationApi
@Preview("Action preview", widthDp = 360, heightDp = 640)
@Composable
fun ActionPreview() {
    MyTheme(darkTheme = true) {
        ActionScene()
    }
}
