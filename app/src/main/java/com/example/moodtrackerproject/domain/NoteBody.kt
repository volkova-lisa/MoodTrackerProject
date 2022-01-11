package com.example.moodtrackerproject.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NoteBody(
    val noteId: String = System.currentTimeMillis().toString(),
    val date: String = "",
    val title: String = "",
    val text: String = " ",
    var isChecked: Boolean = false,
    var isDeleted: Boolean = false
)
