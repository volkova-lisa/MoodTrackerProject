package com.example.moodtrackerproject.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.data.NoteBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel : ViewModel() {

    private val state = AddNewNoteViewState()
    val database = DataBaseRepository()
    var allNotes = database.allNotes

    private val _addNewNoteStateLiveData: MutableLiveData<AddNewNoteViewState> =
        MutableLiveData<AddNewNoteViewState>().apply {
            value = state
        }
    val liveData get() = _addNewNoteStateLiveData

    fun checkNoteData(title: String, text: String) {
        if (title.isEmpty())
            liveData.value = state.copy(error = NewNoteError.ShowEmptyTitle)
        // else insertNewNote(title, text)
        else insertNewNote(NoteBody(title = title, text = text))
    }

    private fun insertNewNote(note: NoteBody) =
        viewModelScope.launch(Dispatchers.Main) {
//          here i have to input note in livedata allNotes
            database.insert(note) {
                state.copy(action = NewNoteAction.ShowNotesScreen)
            }
        }
}
