package com.example.moodtrackerproject.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodtrackerproject.data.NoteBody
import com.example.moodtrackerproject.data.NotesLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel : ViewModel() {

    private val state = AddNewNoteViewState()
    private var database: LiveData<List<NoteBody>> = NotesLiveData()

    private val _addNewNoteStateLiveData: MutableLiveData<AddNewNoteViewState> =
        MutableLiveData<AddNewNoteViewState>().apply {
            value = state
        }
    private val liveData get() = _addNewNoteStateLiveData

    fun checkNoteData(title: String, text: String) {
        if (title.isNotEmpty())
            liveData.value = state.copy(error = NewNoteError.ShowEmptyTitle)
        // else insertNewNote(title, text)
    }

    private fun insertNewNote(note: NoteBody, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.Main) {

        }
}
