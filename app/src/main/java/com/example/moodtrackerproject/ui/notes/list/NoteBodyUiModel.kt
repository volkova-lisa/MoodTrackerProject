package com.example.moodtrackerproject.ui.notes.list

data class NoteBodyUiModel(
    val noteId: String = "", // text.hashCode()//UUID.randomUUID().toString(), //System.currentTimeMillis(),"",
    val date: String = "",
    val title: String = "",
    val text: String = " ",
    val isChecked: Boolean = false,
    val isDeleted: Boolean = false,
    val checkChanged: ((String) -> Unit)? = null,
    val openDetails: ((NoteBodyUiModel) -> Unit)? = null,
    val deleteNote: ((String) -> Unit)? = null
)
