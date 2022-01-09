package com.example.moodtrackerproject.domain

import com.squareup.moshi.JsonClass
// @serializable
@JsonClass(generateAdapter = true)
data class NoteBody(
    val date: String = "",
    val title: String = "",
    val text: String = " "
)
