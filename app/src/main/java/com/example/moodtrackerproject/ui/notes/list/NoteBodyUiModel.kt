package com.example.moodtrackerproject.ui.notes.list

data class NoteBodyUiModel(
    var noteId: String = "", // text.hashCode()//UUID.randomUUID().toString(), //System.currentTimeMillis(),"",

    val date: String = "",
    var title: String = "",
    var text: String = " ",
    val isChecked: Boolean = false,

    val checkChanged: (String) -> Unit,
    val openDetails: (String) -> Unit,
)
