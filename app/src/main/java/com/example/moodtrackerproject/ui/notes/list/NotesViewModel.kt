package com.example.moodtrackerproject.ui.notes.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.domain.map.NotesMapper
import com.example.moodtrackerproject.utils.PreferenceManager

class NotesViewModel : ViewModel() {
    //проблема - тут notesLiveData обновляются лишь при нажатии onToolbarStarClicked
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
        Log.d("NOTESVALUE main star clicked", notesLiveData.value.toString())
    }
    private fun mapNotesList(): List<NoteBodyUiModel> {
        return notesList.map { NotesMapper().map(it) }
    }
}
