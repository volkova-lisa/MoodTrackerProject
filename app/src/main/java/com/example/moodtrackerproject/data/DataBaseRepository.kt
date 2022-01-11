package com.example.moodtrackerproject.data

import androidx.lifecycle.MutableLiveData
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.utils.PreferenceManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

// single source of truth
object DataBaseRepository {
    var allNotes = MutableLiveData<MutableList<NoteBody>>(PreferenceManager.getNotes())
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val values: ParameterizedType =
        Types.newParameterizedType(List::class.java, NoteBody::class.java)
    private val jsonAdapter: JsonAdapter<List<NoteBody>> = moshi.adapter(values)

    fun insert(noteBody: NoteBody, onSuccess: () -> Unit) {
        allNotes.value!!.add(noteBody)
        val notesList: List<NoteBody> = allNotes.value!!
        val serNotes = jsonAdapter.toJson(notesList)
        PreferenceManager.setNotes(serNotes)
    }

    fun delete(noteBody: NoteBody, onSuccess: () -> Unit) {
    }

    fun filterFavNotes() {
        var filteredList = emptyList<NoteBody>()
        filteredList = allNotes.value!!.filter { note -> note.isChecked }
    }

    fun setFavorite(noteBody: NoteBody) {
        allNotes.value = allNotes.value?.map {
            if (it.noteId == noteBody.noteId) it.copy(isChecked = !it.isChecked) else it
        } as MutableList<NoteBody>?
    }
}
