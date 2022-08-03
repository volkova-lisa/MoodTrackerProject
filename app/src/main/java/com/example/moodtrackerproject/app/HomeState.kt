package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.domain.*

data class HomeState(
    val listOfMoods: List<MoodModel> = listOf(),
    val listOfNotes: List<NoteModel> = listOf(),
    val isLoggedIn: Boolean = false,
    val healthModel: HealthModel? = null,
    val resultModel: ResultsModel? = null,
    val name: String = "",
    val email: String = "",
    val photo: String = "",
    val healthMax: MaxHealthModel = MaxHealthModel(),
)
