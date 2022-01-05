package com.example.moodtrackerproject.data

import androidx.lifecycle.MutableLiveData
import kotlinx.serialization.Serializable

@Serializable
object DataBaseRepository {
    var allNotes = MutableLiveData<MutableList<NoteBody>>(mutableListOf())
    var favoriteNotes = MutableLiveData<MutableList<NoteBody>>(mutableListOf())

    fun insert(noteBody: NoteBody, onSuccess: () -> Unit) {
        allNotes.value!!.add(noteBody)
        var notesList: List<NoteBody> = allNotes.value!!
        // Preference.saveNotes(Json.encodeToString(notesList))
    }

    fun delete(noteBody: NoteBody, onSuccess: () -> Unit) {
        allNotes.value!!.remove(noteBody)
    }

    fun addToFavorites(noteBody: NoteBody) {
        favoriteNotes.value!!.add(noteBody)
    }

    fun removeFromFavorites(noteBody: NoteBody) {
        favoriteNotes.value!!.remove(noteBody)
    }
}
