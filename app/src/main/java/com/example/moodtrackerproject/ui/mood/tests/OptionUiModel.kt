package com.example.moodtrackerproject.ui.mood.tests

data class OptionUiModel(
    val text: String = "",
    val points: Int = 0,
    val isChecked: Boolean = false,
    val checkChanged: ((String) -> Unit)? = null

)
