package com.example.moodtrackerproject.domain

data class MoodModel(
    val moodId: String = System.currentTimeMillis().toString(),
    val emojiSrc: Int = 0,
    val moodTitle: String = "",
    val moodTime: String = " ",
    val isDeleted: Boolean = false
)
