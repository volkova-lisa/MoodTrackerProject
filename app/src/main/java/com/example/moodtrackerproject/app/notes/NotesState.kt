package com.example.moodtrackerproject.app.notes

import com.example.moodtrackerproject.domain.NoteModel

data class NotesState(
    val isFavoriteChecked: Boolean = false,
    val currentId: String = "",
    val listOfNotes: List<NoteModel> = listOf(),
)
