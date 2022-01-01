package com.example.moodtrackerproject.data

import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
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
}
