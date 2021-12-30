package com.example.moodtrackerproject.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NotesLiveData : LiveData<List<NoteBody>>() {
    private val listener = object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {
        }
        override fun onDataChange(data: DataSnapshot) {
            var value = data.children.map {
                it.getValue(NoteBody::class.java) ?: NoteBody()
            }
        }
    }

//    override fun onInactive() {
//        DatabaseReference.removeEventListener(listener)
//        super.onInactive()
//    }
//
//    override fun onActive() {
//        DatabaseReference.addValueEventListener(listener)
//        super.onActive()
//    }

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
