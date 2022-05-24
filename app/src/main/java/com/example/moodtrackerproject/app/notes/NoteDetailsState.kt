package com.example.moodtrackerproject.app.notes

import com.example.moodtrackerproject.domain.NoteModel

data class NoteDetailsState(
    val isEditNoteVisible: Boolean = false,
    val isFavoriteChecked: Boolean = false,
    val noteModel: NoteModel = NoteModel(),
)
