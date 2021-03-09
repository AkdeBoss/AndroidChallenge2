package com.example.androiddevchallenge.models

data class CountDownModel(
    val days:Int=0,
    val hours: Int =0,
    val minutes:Int=0,
    val seconds:Int=0) {
    fun isCritical(): Boolean = days==0&&hours==0&&minutes==0&&seconds<4

}
