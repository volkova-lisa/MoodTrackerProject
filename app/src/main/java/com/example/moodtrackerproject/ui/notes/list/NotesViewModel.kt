package com.example.moodtrackerproject.ui.notes.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.utils.PreferenceManager

class NotesViewModel : ViewModel() {

    private val notesList: MutableList<NoteBody>? = if (PreferenceManager.getNotes() == null) mutableListOf() else PreferenceManager.getNotes()

    fun getAllNotes(): MutableLiveData<List<NoteBody>?> = MutableLiveData(notesList)
    fun getFavNotes(): MutableLiveData<MutableList<NoteBody>> = DataBaseRepository.favoriteNotes

    // map NoteBody to NoteBodyUiModel
    // fun getAllNotes
    // fun getFavNotes
}
