package com.example.moodtrackerproject.ui.notes.add

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.AddNewNoteViewState
import com.example.moodtrackerproject.ui.notes.NewNoteAction
import com.example.moodtrackerproject.ui.notes.NewNoteError
import com.example.moodtrackerproject.utils.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel : ViewModel() {

    private val state = AddNewNoteViewState()
    private val _addNewNoteStateLiveData: MutableLiveData<AddNewNoteViewState> =
        MutableLiveData<AddNewNoteViewState>().apply {
            value = state
        }
    private val dateOfNote = DateUtils()
    val liveData get() = _addNewNoteStateLiveData

    @SuppressLint("SimpleDateFormat")
    fun checkNoteData(title: String, text: String) {
        if (title.isEmpty())
            liveData.value = state.copy(error = NewNoteError.ShowEmptyTitle)
        else insertNewNote(NoteBody(date = dateOfNote.getDateOfNote(), title = title, text = text))
    }

    private fun insertNewNote(note: NoteBody) =
        viewModelScope.launch(Dispatchers.Main) {
            DataBaseRepository.insert(note) {
                state.copy(action = NewNoteAction.ShowNotesScreen)
            }
        }
}
