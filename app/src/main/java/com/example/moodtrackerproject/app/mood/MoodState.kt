package com.example.moodtrackerproject.app.mood

import com.example.moodtrackerproject.ui.mood.list.MoodProps

data class MoodState(
    val listOfMoods: List<MoodProps.MoodItemProps> = listOf(),
)
