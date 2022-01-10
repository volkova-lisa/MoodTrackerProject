package com.example.moodtrackerproject.domain.map

import android.util.Log
import com.example.moodtrackerproject.data.DataBaseRepository
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

    override fun map(model: NoteBody) = NoteBodyUiModel().also {
        it.noteId = model.noteId
        it.date = model.date
        it.title = model.title
        it.text = model.text
        it.isChecked = model.isChecked
        // TODO("change")
        it.checkChanged = { changed ->
            Timber.d(changed)
            Log.d("HELLO", "HELLO")
            DataBaseRepository.setFavorite(model)
        }
        it.openDetails = { open -> Timber.d(open) }
    }
}
