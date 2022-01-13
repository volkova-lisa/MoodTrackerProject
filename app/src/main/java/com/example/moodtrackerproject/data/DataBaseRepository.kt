package com.example.moodtrackerproject.data

import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.utils.PreferenceManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

// single source of truth
object DataBaseRepository {
    var allNotes: MutableList<NoteBody> = PreferenceManager.getNotes()!!.toMutableList()
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val values: ParameterizedType =
        Types.newParameterizedType(List::class.java, NoteBody::class.java)
    private val jsonAdapter: JsonAdapter<List<NoteBody>> = moshi.adapter(values)

    fun insert(noteBody: NoteBody, onSuccess: () -> Unit) {
        allNotes.add(noteBody)
        PreferenceManager.setNotes(serializeNotes(allNotes))
    }

    // TODO("ask before delete")
    fun setDeleted(noteBody: NoteBody) {
        allNotes = allNotes.map {
            if (it.noteId == noteBody.noteId) it.copy(isDeleted = !it.isDeleted) else it
        } as MutableList<NoteBody>
        PreferenceManager.setNotes(serializeNotes(allNotes))
    }

    fun removeDeletedNotes() {
        allNotes = allNotes.filter { !it.isDeleted } as MutableList<NoteBody>
        PreferenceManager.setNotes(serializeNotes(allNotes))
    }

    private fun serializeNotes(list: MutableList<NoteBody>): String {
        val notesList: List<NoteBody> = allNotes
        return jsonAdapter.toJson(notesList)
    }

    fun setFavorite(noteBody: NoteBody) {
        allNotes = allNotes.map {
            if (it.noteId == noteBody.noteId) it.copy(isChecked = !it.isChecked) else it
        } as MutableList<NoteBody>
        PreferenceManager.setNotes(serializeNotes(allNotes))
    }
}
