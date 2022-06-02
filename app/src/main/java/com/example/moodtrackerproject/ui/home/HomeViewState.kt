package com.example.moodtrackerproject.ui.home

import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.mood.list.MoodBody

data class HomeViewState(
    val isLoggedIn: Boolean = false,
    val listOfMoods: List<MoodBody> = listOf(),
    val listOfNotes: List<NoteBody> = listOf(),
    val action: HomeAction? = null
)

sealed class HomeAction {
    object LogOut : HomeAction()
}
