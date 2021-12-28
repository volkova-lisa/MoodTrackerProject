package com.example.moodtrackerproject.data

import androidx.lifecycle.LiveData

class FirebaseRepository : DataBaseRepository {

    override val allNotes: LiveData<List<NoteBody>> = NotesLiveData()
    override suspend fun insert(noteBody: NoteBody, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(noteBody: NoteBody, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }
}
