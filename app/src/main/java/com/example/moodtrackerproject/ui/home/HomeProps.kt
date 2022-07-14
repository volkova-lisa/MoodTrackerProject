package com.example.moodtrackerproject.ui.home

import com.example.moodtrackerproject.ui.mood.list.MoodProps
import com.example.moodtrackerproject.ui.notes.list.NotesListProps

data class HomeProps(
    val isLoggedIn: Boolean = false,
    val logout: () -> Unit,
    val action: HomeAction? = null,
    val listOfMoodsToday: List<MoodProps.MoodItemProps> = listOf(),
    val listOfNotesToday: List<NotesListProps.NoteItemProps> = listOf(),
    val fetchListOfMoods: () -> Unit = {},
    val fetchListOfNotes: () -> Unit = {},

) {
    sealed class HomeAction {
        object LogOut : HomeAction()
    }
}
