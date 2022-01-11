package com.example.moodtrackerproject.ui.notes.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.utils.PreferenceManager

class NotesViewModel : ViewModel() {

    // TODO("")
    // how can i take allnotes from repository adequately???
    // how should i delete marked as deleted?

    private val state = NotesViewState()
    private val _notesStateLiveData: MutableLiveData<NotesViewState> =
        MutableLiveData<NotesViewState>().apply {
            value = state
        }
    val liveData get() = _notesStateLiveData
    val uiModels: MutableLiveData<MutableList<NoteBody>> = DataBaseRepository.allNotes

    fun onToolbarStarClicked(checked: Boolean) {
        if (checked) uiModels.value = uiModels.value!!.filter { it.isChecked }.toMutableList()
        else uiModels.value = PreferenceManager.getNotes()
    }
}
