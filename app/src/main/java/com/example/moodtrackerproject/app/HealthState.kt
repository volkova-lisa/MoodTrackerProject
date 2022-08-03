package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.domain.HealthModel
import com.example.moodtrackerproject.domain.MaxHealthModel

data class HealthState(
    val edited: Boolean = false,
    val healthModel: HealthModel? = null,
    val healthMax: MaxHealthModel = MaxHealthModel(),
)
