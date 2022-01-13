package com.example.moodtrackerproject.ui.notes.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.domain.map.NotesMapper
import com.example.moodtrackerproject.utils.PreferenceManager

class NotesViewModel : ViewModel() {
    private val state = NotesViewState()
    private val _notesStateLiveData: MutableLiveData<NotesViewState> =
        MutableLiveData<NotesViewState>().apply {
            value = state
        }
    val liveData get() = _notesStateLiveData
    var notesList: MutableList<NoteBody> = DataBaseRepository.allNotes
    var notesLiveData: MutableLiveData<List<NoteBodyUiModel>> = MutableLiveData(mapNotesList())

    fun onToolbarStarClicked(checked: Boolean) {
        if (checked) notesLiveData.value = notesLiveData.value!!.filter { it.isChecked }.toMutableList()
        else notesLiveData.value = PreferenceManager.getNotes()!!.map { NotesMapper().map(it) }.toMutableList()
    }

    // notesList.reversed().map { NotesMapper().map(notesList)}

    fun mapNotesList(): List<NoteBodyUiModel> {
        return notesList.map { NotesMapper().map(it) }
    }
}
