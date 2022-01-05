package com.example.moodtrackerproject.data

import java.io.Serializable
// @serializable
data class NoteBody(
    var date: String = "",
    val title: String = "",
    val text: String = " "
) : Serializable
