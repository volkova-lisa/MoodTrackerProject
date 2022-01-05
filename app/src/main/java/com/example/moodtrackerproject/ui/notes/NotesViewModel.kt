package com.example.moodtrackerproject.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.data.NoteBody
import com.example.moodtrackerproject.utils.Preference
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

class NotesViewModel : ViewModel() {
    var isChecked: Boolean = false

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val values: ParameterizedType = Types.newParameterizedType(List::class.java, NoteBody::class.java)
    private val jsonAdapter: JsonAdapter<MutableList<NoteBody>> = moshi.adapter(values)
    private val jsonResponse = Preference.getNotes()
    private val notesList: MutableList<NoteBody>? = if (jsonResponse == null) mutableListOf() else jsonAdapter.fromJson(jsonResponse)

    fun getAllNotes(): MutableLiveData<MutableList<NoteBody>?> = MutableLiveData(notesList)
    fun getFavNotes(): MutableLiveData<MutableList<NoteBody>> = DataBaseRepository.favoriteNotes
}
