package com.example.moodtrackerproject.data

import androidx.lifecycle.LiveData

interface DataBaseRepository {
    // should:
    // get list of all notes
    // add note to db
    // remove note from db
    val allNotes: LiveData<List<NoteBody>>
    suspend fun insert(noteBody: NoteBody, onSuccess: () -> Unit)
    suspend fun delete(noteBody: NoteBody, onSuccess: () -> Unit)

    fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {}
}
