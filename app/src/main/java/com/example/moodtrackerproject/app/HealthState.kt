package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.domain.HealthModel

data class HealthState(
    val edited: Boolean = false,
    val listOfHealth: HealthModel? = null
)
