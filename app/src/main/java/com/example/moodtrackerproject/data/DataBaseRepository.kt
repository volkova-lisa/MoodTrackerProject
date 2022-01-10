package com.example.moodtrackerproject.data

import androidx.lifecycle.MutableLiveData
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.list.NoteBodyUiModel
import com.example.moodtrackerproject.utils.PreferenceManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

// single source of truth
object DataBaseRepository {
    val allNotes = MutableLiveData<MutableList<NoteBodyUiModel>>(mutableListOf())
    val favoriteNotes = MutableLiveData<MutableList<NoteBody>>(mutableListOf())
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val values: ParameterizedType =
        Types.newParameterizedType(List::class.java, NoteBody::class.java)
    private val jsonAdapter: JsonAdapter<List<NoteBody>> = moshi.adapter(values)

    fun insert(noteBody: NoteBody, onSuccess: () -> Unit) {

        allNotes.value = PreferenceManager.getNotes()
        allNotes.value!!.add(noteBody)
        // allNotes.value?.let{.add(noteBody)}
        var notesList: List<NoteBody> = allNotes.value!!
        val serNotes = jsonAdapter.toJson(notesList)
        // here i have to concat new notes with previous
        PreferenceManager.setNotes(serNotes)

//        favoriteNotes = DataBaseRepository.favoriteNotes.value?.map {
//            if (it.noteId == noteId) {
//                it.copy(isChecked = !it.checked)
//            } else {
//                it
//            }
//        }
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
