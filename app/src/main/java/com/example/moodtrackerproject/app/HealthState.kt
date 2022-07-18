package com.example.moodtrackerproject.app

data class HealthState(
    val isEdited: Boolean = false,
    val water: Int = 0,
    val steps: Int = 0,
    val sleep: Int = 0,
    val kcal: Int = 0,
)
