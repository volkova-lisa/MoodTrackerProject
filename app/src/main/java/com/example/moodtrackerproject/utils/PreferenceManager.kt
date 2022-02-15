package com.example.moodtrackerproject.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.mood.list.MoodBody
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType

object PreferenceManager {
    private const val KEY_INIT_USER = "init_user"
    private const val PREF_NAME = "pref"
    private const val KEY_NOTES = "all_notes"
    private const val KEY_MOODS = "all_moods"

    private lateinit var preferences: SharedPreferences

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val values: ParameterizedType =
        Types.newParameterizedType(List::class.java, NoteBody::class.java)
    private val notesJsonAdapter: JsonAdapter<List<NoteBody>> = moshi.adapter(values)
    private val moodsJsonAdapter: JsonAdapter<List<MoodBody>> = moshi.adapter(values)

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

    fun getInitUser(): Boolean {
        return preferences.getBoolean(KEY_INIT_USER, false)
    }

    // NOTES----------------
    fun saveNotes(notesList: List<NoteBody>) {
        val serializedNotes = notesJsonAdapter.toJson(notesList)
        preferences.edit()
            .putString(KEY_NOTES, serializedNotes)
            .apply()
    }

    fun getNotes(): List<NoteBody> {
        val notesJson = preferences.getString(KEY_NOTES, null)
        return if (notesJson.isNullOrEmpty()) listOf() else notesJsonAdapter.fromJson(notesJson)
            ?: listOf()
    }

    // MOODS---------
    fun saveMoods(moodList: List<MoodBody>) {
        val serializedMoods = moodsJsonAdapter.toJson(moodList)
        preferences.edit()
            .putString(KEY_MOODS, serializedMoods)
            .apply()
    }

    fun getMoods(): List<MoodBody> {
        val moodsJson = preferences.getString(KEY_MOODS, null)
        return if (moodsJson.isNullOrEmpty()) listOf() else moodsJsonAdapter.fromJson(moodsJson)
            ?: listOf()
    }
}
