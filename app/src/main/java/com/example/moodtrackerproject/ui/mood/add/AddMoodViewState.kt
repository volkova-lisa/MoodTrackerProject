package com.example.moodtrackerproject.ui.mood.add

import com.example.moodtrackerproject.ui.mood.list.MoodBody

data class AddMoodViewState(
    val cancelAdding: () -> Unit = {},
    val saveMood: (MoodBody) -> Unit = {}
)
