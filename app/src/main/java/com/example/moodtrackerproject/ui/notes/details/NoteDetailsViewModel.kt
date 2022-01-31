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
        liveData.value = Store.appState.noteDetailsState.copy(
            currentNote = Store.appState.notesState.listOfNotes[0]
        )
        Store.setState(liveData.value!!)
    }

    fun saveEdited(title: String, text: String) {
        Store.saveEdited(title, text)
        val neededItem =
            DataBaseRepository.getNotes().find { it.noteId == Store.getState().currentId }
        val index =
            DataBaseRepository.getNotes().indexOfFirst { it.noteId == Store.getState().currentId }
        val newNeeded =
            neededItem?.copy(
                title = Store.appState.notesState.listOfNotes[0].title,
                text = Store.appState.notesState.listOfNotes[0].text,
                editDate = "edited " + DateUtils.getDateOfNote()
            )
        val newList = DataBaseRepository.getNotes().toMutableList()
        newList[index] = newNeeded!!
        DataBaseRepository.saveEditedNotes(newList)
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
