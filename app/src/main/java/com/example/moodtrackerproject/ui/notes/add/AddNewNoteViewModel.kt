package com.example.moodtrackerproject.ui.notes.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.utils.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel : ViewModel() {

    private var state: AddNewNoteViewState
    init {
        state = AddNewNoteViewState(
            cancelAdding = ::cancelAdding,
            saveNewNote = ::insertNewNote,
            checkNoteData = ::checkNoteData
        )
    }
    private val _addNewNoteStateLiveData: MutableLiveData<AddNewNoteViewState> =
        MutableLiveData<AddNewNoteViewState>().apply {
            value = state
        }
    val liveData get() = _addNewNoteStateLiveData

    private fun cancelAdding() {
        setState(state.copy(action = NewNoteAction.ShowNotesScreen))
    }

    private fun checkNoteData(pair: Pair<String, String>) {
        if (pair.first.isEmpty())
            liveData.value = state.copy(error = NewNoteError.ShowEmptyTitle)
        else insertNewNote(NoteBody(date = DateUtils.getDateOfNote(), title = pair.first, text = pair.second))
    }

    private fun insertNewNote(note: NoteBody) =
        viewModelScope.launch(Dispatchers.Main) {
            DataBaseRepository.insert(note) {
                _addNewNoteStateLiveData.value = state.copy(action = NewNoteAction.ShowNotesScreen)
            }
            setState(state.copy(action = NewNoteAction.ShowNotesScreen))
        }
    private fun setState(newState: AddNewNoteViewState) {
        state = newState
        _addNewNoteStateLiveData.value = state
    }
}
