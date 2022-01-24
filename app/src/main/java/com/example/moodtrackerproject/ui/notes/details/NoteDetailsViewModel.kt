package com.example.moodtrackerproject.ui.notes.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository

class NoteDetailsViewModel : ViewModel() {
    private var state: DetailsViewState

    private lateinit var curId: String

    init {
        state = DetailsViewState(
            editClicked = ::editNote,
            backClicked = ::goToAllNotes,
            saveEdited = ::saveEdited,
            setId = ::setId,
            setNote = ::setNote,
            setText = ::setText,
            setTitle = ::setTitle
        )
    }

    fun setNoteFromUI(id: String) {
        curId = id
    }

    private val _detailsStateLiveData: MutableLiveData<DetailsViewState> =
        MutableLiveData<DetailsViewState>().apply {
            value = state
        }
    val liveData get() = _detailsStateLiveData

    private fun setId() {
        setState(state.copy(currentId = curId))
    }

    private fun setNote() {
        val thisNote = DataBaseRepository.getNotes().find { it.noteId == state.currentId }
        setState(state.copy(currentNote = thisNote))
    }

    private fun setTitle() {
        if (state.currentNote != null) {
            // setState(state.copy(action = DetailsAction.SetTitleField))
        }
    }

    private fun setText() {
        val currentNote = DataBaseRepository.getNotes().find { it.noteId == state.currentId }
        if (currentNote != null) {
            // setState(state.copy(action = DetailsAction.SetTextField))
        }
    }

    private fun saveEdited() {
    }

    private fun goToAllNotes() {
        liveData.value = state.copy(action = DetailsAction.CancelEditing)
    }

    private fun editNote() {
    }
    private fun setState(newState: DetailsViewState) {
        state = newState
        _detailsStateLiveData.value = state
    }
}
