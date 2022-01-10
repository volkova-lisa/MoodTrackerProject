package com.example.moodtrackerproject.ui.notes.list

import timber.log.Timber

data class NoteBodyUiModel(
    var noteId: String = "", // text.hashCode()//UUID.randomUUID().toString(), //System.currentTimeMillis(),"",

    var date: String = "",
    var title: String = "",
    var text: String = " ",
    var isChecked: Boolean = false,

    var checkChanged: (String) -> Unit = { string -> Timber.d("HELLO") },
    var openDetails: (String) -> Unit = { string -> Timber.d("HELLO") },
)
