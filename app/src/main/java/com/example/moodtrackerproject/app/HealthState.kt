package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.domain.HealthModel

data class HealthState(
    val edited: Boolean = false,
    val healthModel: HealthModel? = null,
    val waterMax: Int = 0,
    val stepsMax: Int = 0,
    val sleepMax: Int = 0,
    val kcalMax: Int = 0,
)
