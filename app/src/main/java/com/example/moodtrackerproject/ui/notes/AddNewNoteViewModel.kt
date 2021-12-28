package com.example.moodtrackerproject.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddNewNoteViewModel : ViewModel() {

    private val state = AddNewNoteViewState()

    private val _addNewNoteStateLiveData: MutableLiveData<AddNewNoteViewState> =
        MutableLiveData<AddNewNoteViewState>().apply {
            value = state
        }
    private val liveData get() = _addNewNoteStateLiveData

    fun checkNoteData(title: String, text: String) {
        if (title.isNotEmpty())
            liveData.value = state.copy(error = NewNoteError.ShowEmptyTitle)
    }
}
