package com.example.moodtrackerproject.ui.notes.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.notes.Store
import com.example.moodtrackerproject.utils.DateUtils

class NoteDetailsViewModel : ViewModel() {

    private var state: DetailsViewState

    init {
        state = Store.appState.noteDetailsState.copy(
            editClicked = ::editNote,
            changeEditVisibility = ::changeEditScreenVisibility,
            backClicked = ::goToAllNotes,
            cancelClicked = ::cancelClicked,
            setNote = ::setNote
        )
        Store.setState(state)
    }

    private val _detailsStateLiveData: MutableLiveData<DetailsViewState> =
        MutableLiveData<DetailsViewState>().apply {
            value = state
        }
    val liveData get() = _detailsStateLiveData

    private fun setNote() {
        val state = Store.appState.noteDetailsState.copy(
            currentNote = Store.appState.notesState.listOfNotes[0]
        )
        liveData.value = state
        Store.setState(state)
    }

    fun saveEdited(title: String, text: String) {
        Store.saveEdited(title, text)
        val neededItem =
            DataBaseRepository.getNotes().find { it.noteId == Store.getNotesState().currentId }
        val index =
            DataBaseRepository.getNotes().indexOfFirst { it.noteId == Store.getNotesState().currentId }
        val newNeeded =
            neededItem?.copy(
                title = Store.appState.notesState.listOfNotes[0].title,
                text = Store.appState.notesState.listOfNotes[0].text,
                editDate = DateUtils.getDateOfNote()
            )
        val newList = DataBaseRepository.getNotes().toMutableList()
        if (newNeeded != null) newList[index] = newNeeded
        DataBaseRepository.saveNotes(newList)
    }

    private fun changeEditScreenVisibility() {
        val isEditNoteVisible = !Store.appState.noteDetailsState.isEditNoteVisible
        val stateNew = state.copy(
            isEditNoteVisible = isEditNoteVisible,
        )
        liveData.value = stateNew

        Store.setState(stateNew)
    }

    private fun goToAllNotes() {
        liveData.value = state.copy(action = DetailsAction.ShowAllNotes)
    }

    private fun cancelClicked() {
        liveData.value = state.copy(action = DetailsAction.CancelEditing)
    }

    private fun editNote() {
    }
}
