package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.domain.MaxHealthModel

data class EditHealthState(
    val healthMax: MaxHealthModel = MaxHealthModel(),
)
