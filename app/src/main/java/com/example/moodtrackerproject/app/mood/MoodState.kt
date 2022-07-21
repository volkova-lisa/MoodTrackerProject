package com.example.moodtrackerproject.app.mood

import com.example.moodtrackerproject.domain.MoodModel

data class MoodState(
    val listOfMoods: List<MoodModel> = listOf(),
    val testType: Int = 6
)
