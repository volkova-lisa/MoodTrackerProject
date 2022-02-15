package com.example.moodtrackerproject.ui.mood.list

import com.example.moodtrackerproject.ui.notes.list.NoteBodyUiModel

data class MoodViewState(
    val listOfMoods: List<NoteBodyUiModel> = listOf(),
)