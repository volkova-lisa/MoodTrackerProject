package com.example.moodtrackerproject.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.moodtrackerproject.domain.NoteBody
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

object PreferenceManager {
    private const val KEY_INIT_USER = "init_user"
    private const val PREF_NAME = "pref"
    private const val KEY_NOTES = "all_notes"
    private lateinit var preferences: SharedPreferences
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val values: ParameterizedType = Types.newParameterizedType(List::class.java, NoteBody::class.java)
    private val jsonAdapter: JsonAdapter<MutableList<NoteBody>> = moshi.adapter(values)

    fun getPreference(context: Context): PreferenceManager {
        if (!::preferences.isInitialized) {
            preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }
        return this
    }

    fun setInitUser(init: Boolean) {
        preferences.edit()
            .putBoolean(KEY_INIT_USER, init)
            .apply()
    }

    fun setNotes(notesList: String) {
        preferences.edit()
            .putString(KEY_NOTES, notesList)
            .apply()
    }

    fun getInitUser(): Boolean {
        return preferences.getBoolean(KEY_INIT_USER, false)
    }

    fun getNotes(): List<NoteBody>? {
        val str = preferences.getString(KEY_NOTES, null)
        return if (str != null && str.isNotEmpty()) {
            val list = jsonAdapter.fromJson(str)
            list
        } else {
            mutableListOf()
        }
//        return jsonAdapter.fromJson(preferences.getString(NOTES, ""))
    }

    fun getJsonNotes(): String? {
        return preferences.getString(KEY_NOTES, null)
    }
}
