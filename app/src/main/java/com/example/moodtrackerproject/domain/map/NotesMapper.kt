package com.example.moodtrackerproject.domain.map

import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.list.NoteBodyUiModel
import timber.log.Timber

class NotesMapper : Mapper<NoteBody, NoteBodyUiModel> {
    private fun mapFrom(date: String, title: String, text: String) = NoteBodyUiModel().also {
        it.noteId = System.currentTimeMillis().toString()
        it.date = date
        it.title = title
        it.text = text
        it.isChecked = false
        // TODO("change")
        it.checkChanged = { changed -> Timber.d(changed) }
        it.openDetails = { open -> Timber.d(open) }
    }

    override fun map(from: NoteBody) = NoteBodyUiModel().apply {
        // TODO("Not yet implemented")
    }
}
