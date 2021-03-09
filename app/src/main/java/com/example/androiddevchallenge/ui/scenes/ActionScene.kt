package com.example.androiddevchallenge.ui.scenes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.models.CountDownModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.viewmodels.CountDownViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ActionScene(modifier: Modifier = Modifier,viewModel: CountDownViewModel? = null) {
      val countDown: Long by viewModel!!.countDown.observeAsState(0)
      val isTicking: Boolean by viewModel!!.isTicking.observeAsState(false)


      Column(modifier = Modifier.padding(16.dp).fillMaxWidth().fillMaxHeight()) {
            Spacer(modifier = Modifier.weight(1.0f, true).height(20.dp))
            ClockGrid(modifier =Modifier.weight(1.0f, true).padding(16.dp).fillMaxWidth(),countDown=countDown,isTicking=isTicking)
            Spacer(modifier = Modifier.weight(1.0f, true).height(20.dp))


            androidx.compose.animation.AnimatedVisibility(visible = isTicking) {
                  Button(modifier = Modifier.weight(1.0f, false).padding(12.dp).fillMaxWidth(),onClick = {
                        viewModel!!.stopTicking()
                  },colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
                        Text(text="Stop it!!",color= Color.White)
                  }
            }
            androidx.compose.animation.AnimatedVisibility(visible = !isTicking) {
                  Button(modifier = Modifier.weight(1.0f, false).padding(12.dp).fillMaxWidth(),onClick = {
                        viewModel!!.stopSession()
                  },colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
                        Text(text="Close",color= Color.White)
                  }
            }


      }
}

@Composable
fun ClockGrid(modifier: Modifier = Modifier,countDown:Long=0, isTicking:Boolean=false) {
            Row(modifier = modifier.fillMaxWidth()) {
                  RotatingDial(
                        modifier = Modifier
                              .weight(1.0f, true)
                              .fillMaxWidth()
                              .fillMaxHeight(), countDown
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