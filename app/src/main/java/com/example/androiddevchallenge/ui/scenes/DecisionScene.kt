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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily.Companion.Monospace
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewmodels.CountDownViewModel

@Composable
fun OptionsGrid(modifier: Modifier = Modifier, viewModel: CountDownViewModel? = null) {
    Column(
        modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            Modifier.padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = " G.A.M ",
                style = TextStyle(
                    fontFamily = Monospace,
                    textDecoration = TextDecoration.combine(
                        listOf(
                            TextDecoration.Underline
                        )
                    ),
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondary
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel!!.startTicking(10)
            }
        ) {
            Text(text = "Gimme 10 seconds", color = MaterialTheme.colors.secondary)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel!!.startTicking(60)
            }
        ) {
            Text(text = "Gimme a minute!!", color = MaterialTheme.colors.secondary)
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel!!.startTicking(600)
            }
        ) {
            Text(text = "Gimme 10 minutes!!", color = MaterialTheme.colors.secondary)
        }

        Spacer(modifier = Modifier.height(16.dp))
//        Button(modifier = Modifier
//            .padding(16.dp)
//            .fillMaxWidth(), onClick = {
//            viewModel!!.startTicking()
//        }) {
//            Text(text = "I need more time.", color = MaterialTheme.colors.secondary)
//        }
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        OptionsGrid()
    }
}
