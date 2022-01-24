package com.example.moodtrackerproject.ui.notes.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.Store

class NoteDetailsViewModel : ViewModel() {

    private var state: DetailsViewState

    init {
        state = Store.appState.noteDetailsState.copy(
            editClicked = ::editNote,
            backClicked = ::goToAllNotes,
            saveEdited = ::saveEdited,
            setId = ::setId,
            setNote = ::setNote
        )
        Store.setState(state)
    }

    private val _detailsStateLiveData: MutableLiveData<DetailsViewState> =
        MutableLiveData<DetailsViewState>().apply {
            value = state
        }
    val liveData get() = _detailsStateLiveData

    private fun setId() {
        liveData.value = state.copy(currentId = Store.getNotesId())
        // Store.setState(liveData.value!!)
    }

    private fun setNote() {
        // val thisNote = Store.appState.notesState.listOfNotes.find { it.noteId == state.currentId }
        val thisNote = NoteBody("1111", "55", "999", "fvdfb", false, false)
        liveData.value = Store.appState.noteDetailsState.copy(
            currentNote = thisNote,
            currentTitle = thisNote!!.title,
            currentText = thisNote!!.text
        )
        Store.setState(liveData.value!!)
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
        liveData.value = state
    }
}
