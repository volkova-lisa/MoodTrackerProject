package com.example.moodtrackerproject.ui.home

import com.example.moodtrackerproject.domain.ResultsModel
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
    val fetchHealth: () -> Unit = {},
    val fetchResults: () -> Unit = {},
    val fetchName: () -> Unit = {},
    val healthItems: HomeItemProps? = null,
    val testResults: ResultsModel? = null,
    val name: String = "",
    val email: String = "",

) {
    sealed class HomeAction {
        object LogOut : HomeAction()
    }

    data class HomeItemProps(
        val water: Int = 0,
        val steps: Int = 0,
        val sleep: Int = 0,
        val kcal: Int = 0,
        val waterMax: Int = 0,
        val stepsMax: Int = 0,
        val sleepMax: Int = 0,
        val kcalMax: Int = 0
    )
}
