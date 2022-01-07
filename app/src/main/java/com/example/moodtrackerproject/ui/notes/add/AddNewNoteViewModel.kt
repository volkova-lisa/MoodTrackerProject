package com.example.moodtrackerproject.ui.notes.add

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.AddNewNoteViewState
import com.example.moodtrackerproject.ui.notes.NewNoteAction
import com.example.moodtrackerproject.ui.notes.NewNoteError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddNewNoteViewModel : ViewModel() {

    private val state = AddNewNoteViewState()
    private val _addNewNoteStateLiveData: MutableLiveData<AddNewNoteViewState> =
        MutableLiveData<AddNewNoteViewState>().apply {
            value = state
        }
    val liveData get() = _addNewNoteStateLiveData

    @SuppressLint("SimpleDateFormat")
    fun checkNoteData(title: String, text: String) {
        val calendar: Calendar = Calendar.getInstance()
        val month = SimpleDateFormat("MMM").format(calendar.get(Calendar.MONTH))
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val noteDate = "$month $day, $hour:$minute"
        if (title.isEmpty())
            liveData.value = state.copy(error = NewNoteError.ShowEmptyTitle)
        else insertNewNote(NoteBody(date = noteDate, title = title, text = text))
    }

    private fun insertNewNote(note: NoteBody) =
        viewModelScope.launch(Dispatchers.Main) {
//          here i have to input note in livedata allNotes
            DataBaseRepository.insert(note) {
                state.copy(action = NewNoteAction.ShowNotesScreen)
            }
        }
}
