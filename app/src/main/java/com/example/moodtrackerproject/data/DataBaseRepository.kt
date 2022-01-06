package com.example.moodtrackerproject.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moodtrackerproject.utils.Preference
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

object DataBaseRepository {
    var allNotes = MutableLiveData<MutableList<NoteBody>>(mutableListOf())
    var favoriteNotes = MutableLiveData<MutableList<NoteBody>>(mutableListOf())
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val values: ParameterizedType =
        Types.newParameterizedType(List::class.java, NoteBody::class.java)
    private val jsonAdapter: JsonAdapter<List<NoteBody>> = moshi.adapter(values)

    fun insert(noteBody: NoteBody, onSuccess: () -> Unit) {

        allNotes.value = Preference.getNotes()
        Log.d("aaaaaaaaaaaaaaaaa", allNotes.value.toString())
        allNotes.value!!.add(noteBody)
        Log.d("bbbbbbbbbbbbbbbb", allNotes.value.toString())
        var notesList: List<NoteBody> = allNotes.value!!
        val serNotes = jsonAdapter.toJson(notesList)
        // here i have to concat new notes with previous
        Preference.setNotes(serNotes)
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
