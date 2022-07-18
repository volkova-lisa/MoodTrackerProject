package com.example.moodtrackerproject.app

data class EditHealthState(
    val save: Boolean = false,
    val waterNum: Int = 0,
    val stepsNum: Int = 0,
    val sleepNum: Int = 0,
    val kcalNum: Int = 0
)
