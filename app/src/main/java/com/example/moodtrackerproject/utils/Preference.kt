package com.example.moodtrackerproject.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.moodtrackerproject.domain.NoteBody
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

object Preference {
    private const val INIT_USER = "init_user"
    private const val PREF = "pref"
    private const val NOTES = "all_notes"
    private lateinit var preferences: SharedPreferences
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val values: ParameterizedType = Types.newParameterizedType(List::class.java, NoteBody::class.java)
    private val jsonAdapter: JsonAdapter<MutableList<NoteBody>> = moshi.adapter(values)

    fun getPreference(context: Context): Preference {
        if (!::preferences.isInitialized) {
            preferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        }
        return this
    }

    fun setInitUser(init: Boolean) {
        preferences.edit()
            .putBoolean(INIT_USER, init)
            .apply()
    }

    fun setNotes(notesList: String) {
        preferences.edit()
            .putString(NOTES, notesList)
            .apply()
    }

    fun getInitUser(): Boolean {
        return preferences.getBoolean(INIT_USER, false)
    }

    fun getNotes(): MutableList<NoteBody>? {
        return jsonAdapter.fromJson(preferences.getString(NOTES, null))
    }

    fun getJsonNotes(): String? {
        return preferences.getString(NOTES, null)
    }
}
