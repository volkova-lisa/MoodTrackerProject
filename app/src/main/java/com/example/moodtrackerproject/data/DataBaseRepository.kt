package com.example.moodtrackerproject.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.database.FirebaseDatabase

class DataBaseRepository {
    // should:
    // get list of all notes
    // add note to db
    // remove note from db
    var allNotes: LiveData<List<NoteBody>> = NotesLiveData()

    fun insert(noteBody: NoteBody, onSuccess: () -> Unit) {
        val idNote = FirebaseDatabase.getInstance().reference.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote["id"] = idNote
        mapNote["title"] = noteBody.title
        mapNote["text"] = noteBody.text

        FirebaseDatabase.getInstance().reference.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                Log.d("______", "________________")
            }
    }

    fun delete(noteBody: NoteBody, onSuccess: () -> Unit) {
        FirebaseDatabase.getInstance().reference.child(noteBody.idFirebase).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                Log.d("++++++++", "++++++++++++++")
            }
    }
}
