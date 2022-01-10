package com.example.moodtrackerproject.ui.notes.list

data class NoteBodyUiModel(
    var noteId: String = "", // text.hashCode()//UUID.randomUUID().toString(), //System.currentTimeMillis(),"",

    var date: String = "",
    var title: String = "",
    var text: String = " ",
    var isChecked: Boolean = false,

    var checkChanged: ((String) -> Unit)? = null,
    var openDetails: ((String) -> Unit)? = null

)
