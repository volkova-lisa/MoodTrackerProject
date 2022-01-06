package com.example.moodtrackerproject.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.data.NoteBody
import com.example.moodtrackerproject.utils.Preference

class NotesViewModel : ViewModel() {
    var isChecked: Boolean = false

    private val notesList: MutableList<NoteBody>? = if (Preference.getNotes() == null) mutableListOf() else Preference.getNotes()

    fun getAllNotes(): MutableLiveData<MutableList<NoteBody>?> = MutableLiveData(notesList)
    fun getFavNotes(): MutableLiveData<MutableList<NoteBody>> = DataBaseRepository.favoriteNotes
}
