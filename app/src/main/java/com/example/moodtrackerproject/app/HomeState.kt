package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.domain.MoodModel
import com.example.moodtrackerproject.domain.NoteModel

data class HomeState(
    val listOfMoods: List<MoodModel> = listOf(),
    val listOfNotes: List<NoteModel> = listOf(),
    val isLoggedIn: Boolean = false
)
