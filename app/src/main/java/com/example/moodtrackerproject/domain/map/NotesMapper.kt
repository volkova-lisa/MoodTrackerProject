package com.example.moodtrackerproject.domain.map

import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.list.NoteBodyUiModel

class NotesMapper : Mapper<NoteBody, NoteBodyUiModel> {
    private fun mapFrom(date: String, title: String, text: String) = NoteBodyUiModel().also {
        it.noteId = ""
        it.title = title
        it.text = text
    }

    override fun map(from: NoteBody): NoteBodyUiModel {
        TODO("Not yet implemented")
    }
}
