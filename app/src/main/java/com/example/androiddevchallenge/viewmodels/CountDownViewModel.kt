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
package com.example.androiddevchallenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.models.CountDownModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountDownViewModel @Inject constructor() : ViewModel() {
    private var tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 0)

    private val _countDown: MutableLiveData<Long> by lazy { MutableLiveData<Long>() }
    val countDown: LiveData<Long> = _countDown

//  private val _countDown : MutableLiveData<CountDownModel> by lazy { MutableLiveData<CountDownModel>() }
//    val countDown: LiveData<CountDownModel> = _countDown

    private val _isTicking: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val isTicking: LiveData<Boolean> = _isTicking

    private var elapsedSeconds: Long = 0
    private var countDownSeconds: Long = 60

    val isTimerSet: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun startTicking(seconds: Long) {
        countDownSeconds = seconds
        elapsedSeconds = 0
        _isTicking.postValue(true)
        isTimerSet.postValue(true)

        tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 0)

        viewModelScope.launch {
            for (event in tickerChannel) {
                elapsedSeconds++
                if (elapsedSeconds == countDownSeconds) {
                    elapsedSeconds = 0
                    stopTicking()
                    break
                } else {
                    _countDown.postValue(countDownSeconds - elapsedSeconds)
                }
            }
        }
    }

    private fun getSecondsToCountDownTimer(elapsedSeconds: Long): CountDownModel {
        val remaining = countDownSeconds - elapsedSeconds
        return if (remaining >= 0) {
            CountDownModel(
                (if (remaining >= 86400) { remaining / 86400 } else { 0 }).toInt(),
                (if (remaining >= 3600) { remaining / 3600 } else { 0 }).toInt(),
                (if (remaining >= 60) { remaining / 60 } else { 0 }).toInt(),
                (if (remaining >= 60) { remaining % 60 } else { remaining }).toInt()
            )
        } else {
            CountDownModel()
        }
    }

    fun stopSession() {
        isTimerSet.postValue(false)
    }
    fun stopTicking() {
        _countDown.postValue(0)
        _isTicking.postValue(false)
        tickerChannel.cancel()
    }
}
