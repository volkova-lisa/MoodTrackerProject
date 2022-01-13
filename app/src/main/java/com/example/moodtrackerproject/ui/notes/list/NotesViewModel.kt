package com.example.moodtrackerproject.ui.notes.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import timber.log.Timber

class NotesViewModel : ViewModel() {
    // проблема - тут notesLiveData обновляются лишь при нажатии onToolbarStarClicked
    private val state = NotesViewState()
    private val _notesStateLiveData: MutableLiveData<NotesViewState> =
        MutableLiveData<NotesViewState>().apply {
            value = state
        }
    val liveData get() = _notesStateLiveData

    // TODO: move list to state - only one liveData per view model!
    val notesLiveData: MutableLiveData<List<NoteBodyUiModel>> = MutableLiveData(map(DataBaseRepository.getNotes()))

    fun onToolbarStarClicked(checked: Boolean) {
        notesLiveData.value = map(
            if (checked) DataBaseRepository.getNotes().filter { it.isChecked }
            else DataBaseRepository.getNotes()
        )
    }

    private fun map(notesList: List<NoteBody>): List<NoteBodyUiModel> {
        return notesList.map { model ->
            NoteBodyUiModel(
                noteId = model.noteId,
                date = model.date,
                title = model.title,
                text = model.text,
                isChecked = model.isChecked,
                isDeleted = model.isDeleted,
                checkChanged = {
                    // with callback
//                    DataBaseRepository.setFavorite(it) {
//                        notesLiveData.value = map(it)
//                    }
                    // or without callback
                    val list = DataBaseRepository.setFavorite(it)
                    notesLiveData.value = map(list)

                    // Note: both implementations are not perfect and will be revised in the future
                },
                openDetails = { open -> Timber.d(open) },
                deleteNote = { deleted ->
                    Timber.d(deleted)
                    // TODO: maybe combine these two functions because they are similar?
                    DataBaseRepository.setDeleted(model)
                    DataBaseRepository.removeDeletedNotes()
                },
            )
        }
    }
}
