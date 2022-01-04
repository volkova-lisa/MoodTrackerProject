package com.example.moodtrackerproject.data

import java.io.Serializable

data class NoteBody(
    val id: String = "",
    val title: String = "",
    val text: String = " "
) : Serializable
