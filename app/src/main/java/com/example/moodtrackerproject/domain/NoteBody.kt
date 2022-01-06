package com.example.moodtrackerproject.domain

import com.squareup.moshi.JsonClass
import java.io.Serializable
// @serializable
@JsonClass(generateAdapter = true)
data class NoteBody(
    var date: String = "",
    val title: String = "",
    val text: String = " "
) : Serializable
