package com.example.moodtrackerproject.domain.map

import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.list.NoteBodyUiModel
import timber.log.Timber

// TODO: move mapping to ViewModel
class NotesMapper : Mapper<NoteBody, NoteBodyUiModel> {
    override fun map(model: NoteBody) = NoteBodyUiModel().also {
        it.noteId = model.noteId
        it.date = model.date
        it.title = model.title
        it.text = model.text
        it.isChecked = model.isChecked
        it.isDeleted = model.isDeleted

        it.checkChanged = { changed ->
            Timber.d(changed)
            DataBaseRepository.setFavorite(model)
        }
        it.openDetails = { open -> Timber.d(open) }
        it.deleteNote = { deleted ->
            Timber.d(deleted)
            DataBaseRepository.setDeleted(model)
            DataBaseRepository.removeDeletedNotes()
        }
    }
}
