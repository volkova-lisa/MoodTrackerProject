package com.example.moodtrackerproject.ui.notes.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody

class NotesViewModel : ViewModel() {
    private val state = NotesViewState()
    private val _notesStateLiveData: MutableLiveData<NotesViewState> =
        MutableLiveData<NotesViewState>().apply {
            value = state
        }
    val liveData get() = _notesStateLiveData
    val uiModels: MutableLiveData<MutableList<NoteBody>> = DataBaseRepository.allNotes

    fun onToolbarStarClicked(isChecked: Boolean) {
        if (isChecked) {
            uiModels.value = uiModels.value!!.filter {
                it.isChecked
            }.toMutableList()
        }
    }
}
