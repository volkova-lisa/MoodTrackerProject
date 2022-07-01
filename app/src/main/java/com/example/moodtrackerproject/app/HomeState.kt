package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.domain.MoodModel

data class HomeState(
    val listOfMoods: List<MoodModel> = listOf(),
    val isLoggedIn: Boolean = false
)
