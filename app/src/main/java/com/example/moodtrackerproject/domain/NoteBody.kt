package com.example.moodtrackerproject.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NoteBody(
    val noteId: String = System.currentTimeMillis().toString(),
    val date: String = "",
    val editDate: String = "",
    val title: String = "",
    val text: String = " ",
    val isChecked: Boolean = false,
    val isDeleted: Boolean = false
)
