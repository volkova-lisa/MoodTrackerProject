package com.example.moodtrackerproject.ui.mood.add

data class EmojiBodyUIModel(
    val image: Int = 0,
    val title: String = "",
    val isChecked: Boolean = false,
    val checkChanged: (EmojiBodyUIModel) -> Unit = {}
)
