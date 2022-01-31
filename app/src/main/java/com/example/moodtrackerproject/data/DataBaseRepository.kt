package com.example.moodtrackerproject.data

import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.utils.PreferenceManager

// single source of truth
object DataBaseRepository {
    fun getNotes() = PreferenceManager.getNotes()

    fun insert(noteBody: NoteBody, onSuccess: () -> Unit) {
        val list = mutableListOf<NoteBody>().apply {
            addAll(getNotes())
            add(noteBody)
        }
        saveNotes(list)
    }

    // TODO("ask before delete")
    fun setDeleted(noteBody: NoteBody) {
        val list = getNotes().map {
            if (it.noteId == noteBody.noteId) it.copy(isDeleted = !it.isDeleted) else it
        }
        saveNotes(list)
    }

    fun removeDeletedNotes(): List<NoteBody> {
        val list = getNotes().filter { !it.isDeleted }
        saveNotes(list)
        return list
    }

    fun saveEditedNotes(notesList: List<NoteBody>) {
        saveNotes(notesList)
    }
    private fun saveNotes(notesList: List<NoteBody>) {
        PreferenceManager.saveNotes(notesList)
    }

    fun setFavorite(noteId: String): List<NoteBody> {
        val list = getNotes().map {
            if (it.noteId == noteId) it.copy(isChecked = !it.isChecked) else it
        }
        saveNotes(list)
        return list
    }
}
