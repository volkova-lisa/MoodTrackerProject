package com.example.moodtrackerproject.ui.notes.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.ui.notes.Store
import com.example.moodtrackerproject.utils.PreferenceManager

class NoteDetailsViewModel : ViewModel() {

    private var state: DetailsViewState

    init {
        state = Store.appState.noteDetailsState.copy(
            editClicked = ::editNote,
            backClicked = ::goToAllNotes,
            cancelClicked = ::cancelClicked,
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

    fun saveEdited(title: String, text: String) {
        Store.saveEdited(title, text)
        // studio is working bad so this state is not working
        Log.d("===--------", Store.getState().currentId.toString())
        Log.d("+++--------", state.noteCurrent!!.noteId)
        val neededItemFromPref =
            PreferenceManager.getNotes().find { it.noteId == Store.getState().currentId }
        val index =
            PreferenceManager.getNotes().indexOfFirst { it.noteId == Store.getState().currentId }
        val newNeeded =
            neededItemFromPref?.copy(
                title = Store.appState.notesState.listOfNotes[0].title,
                text = Store.appState.notesState.listOfNotes[0].text
            )

        Log.d("000--------", PreferenceManager.getNotes().toString())
        Log.d("111--------", neededItemFromPref.toString())
        Log.d("222--------", index.toString())
        Log.d("333--------", newNeeded.toString())

        val newList = PreferenceManager.getNotes().toMutableList()
        newList[index] = newNeeded!!
        PreferenceManager.saveNotes(newList)
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
