package com.example.moodtrackerproject.ui.notes.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteDetailsViewModel : ViewModel() {
    private var state: DetailsViewState

    init {
        state = DetailsViewState(
            editClicked = ::editNote,
            backClicked = ::goToAllNotes,
            saveEdited = ::saveEdited
        )
    }

    private val _detailsStateLiveData: MutableLiveData<DetailsViewState> =
        MutableLiveData<DetailsViewState>().apply {
            value = state
        }
    val liveData: LiveData<DetailsViewState> get() = _detailsStateLiveData

    private fun saveEdited() {

    }


    private fun goToAllNotes() {

    }

    private fun editNote() {

    }

}