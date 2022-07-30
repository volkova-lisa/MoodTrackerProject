package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.domain.HealthModel
import com.example.moodtrackerproject.domain.MoodModel
import com.example.moodtrackerproject.domain.NoteModel
import com.example.moodtrackerproject.domain.ResultsModel

data class HomeState(
    val listOfMoods: List<MoodModel> = listOf(),
    val listOfNotes: List<NoteModel> = listOf(),
    val isLoggedIn: Boolean = false,
    val healthModel: HealthModel? = null,
    val resultModel: ResultsModel? = null,
    val name: String = "ii",
    val email: String = "",
)
