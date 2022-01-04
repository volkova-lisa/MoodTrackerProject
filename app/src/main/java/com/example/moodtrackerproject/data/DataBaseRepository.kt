package com.example.moodtrackerproject.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase

object DataBaseRepository {
    // should:
    // get list of all notes
    // add note to db
    // remove note from db
    var allNotes = MutableLiveData<MutableList<NoteBody>>(mutableListOf())

    fun insert(noteBody: NoteBody, onSuccess: () -> Unit) {
        Log.d("-------------", allNotes.value.toString())
        allNotes.value!!.add(noteBody)
        Log.d("++++++++++++++", allNotes.value.toString())
    }

    fun delete(noteBody: NoteBody, onSuccess: () -> Unit) {
        FirebaseDatabase.getInstance().reference.child(noteBody.id).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                Log.d("++++++++", "++++++++++++++")
            }
    }
}
