package com.example.moodtrackerproject.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.data.NoteBody

class NotesViewModel : ViewModel() {
    fun getAllNotes(): MutableLiveData<MutableList<NoteBody>> = DataBaseRepository.allNotes
    fun getFavNotes(): MutableLiveData<MutableList<NoteBody>> = DataBaseRepository.favoriteNotes
}
