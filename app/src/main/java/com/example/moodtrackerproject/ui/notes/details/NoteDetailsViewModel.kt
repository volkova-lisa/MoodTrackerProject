package com.example.moodtrackerproject.ui.notes.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        liveData.value = state.copy(currentId = Store.getNoteId())
        // Store.setState(liveData.value!!)
    }

    private fun setNote() {
        liveData.value = Store.appState.noteDetailsState.copy(
            currentNote = Store.appState.notesState.listOfNotes[0]
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

}
