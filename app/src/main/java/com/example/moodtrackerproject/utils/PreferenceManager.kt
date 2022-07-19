package com.example.moodtrackerproject.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.moodtrackerproject.domain.MoodModel
import com.example.moodtrackerproject.domain.NoteModel
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
    private const val KEY_HEAlTH = "all_health"
    private const val KEY_TESTS = "all_tests"

    private lateinit var preferences: SharedPreferences

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val valuesNote: ParameterizedType =
        Types.newParameterizedType(List::class.java, NoteModel::class.java)

    private val valuesMood: ParameterizedType =
        Types.newParameterizedType(List::class.java, MoodModel::class.java)

    private val valuesHealth: ParameterizedType =
        Types.newParameterizedType(List::class.java, Integer::class.java)

    private val valuesTests: ParameterizedType =
        Types.newParameterizedType(List::class.java, Integer::class.java)

    private val healthJsonAdapter: JsonAdapter<List<Int>> =
        moshi.adapter<List<Int>>(valuesHealth).nonNull()
    private val testsJsonAdapter: JsonAdapter<List<Int>> =
        moshi.adapter<List<Int>>(valuesTests).nonNull()
    private val notesJsonAdapter: JsonAdapter<List<NoteModel>> = moshi.adapter(valuesNote)
    private val moodsJsonAdapter: JsonAdapter<List<MoodModel>> = moshi.adapter(valuesMood)

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

    fun saveNotes(notesList: List<NoteModel>) {
        val serializedNotes = notesJsonAdapter.toJson(notesList)
        preferences.edit()
            .putString(KEY_NOTES, serializedNotes)
            .apply()
    }

    fun getNotes(): List<NoteModel> {
        val notesJson = preferences.getString(KEY_NOTES, null)
        return if (notesJson.isNullOrEmpty()) listOf() else notesJsonAdapter.fromJson(notesJson)
            ?: listOf()
    }

    fun saveMoods(moodList: List<MoodModel>) {
        val serializedMoods = moodsJsonAdapter.toJson(moodList)
        preferences.edit()
            .putString(KEY_MOODS, serializedMoods)
            .apply()
    }

    fun getMoods(): List<MoodModel> {
        val moodsJson = preferences.getString(KEY_MOODS, null)
        return if (moodsJson.isNullOrEmpty()) listOf() else moodsJsonAdapter.fromJson(moodsJson)
            ?: listOf()
    }

    fun saveHealth(healthList: List<Int>) {
        val serializedHealth = healthJsonAdapter.toJson(healthList)
        preferences.edit()
            .putString(KEY_HEAlTH, serializedHealth)
            .apply()
    }

    fun getHealth(): List<Int> {
        val healthJson = preferences.getString(KEY_HEAlTH, null)
        return if (healthJson.isNullOrEmpty()) listOf(0, 0, 0, 0) else healthJsonAdapter.fromJson(healthJson)
            ?: listOf(0, 0, 0, 0)
    }

    fun saveTests(testsList: Array<Int>) {
        val serializedTests = testsJsonAdapter.toJson(testsList.toList())
        preferences.edit()
            .putString(KEY_TESTS, serializedTests)
            .apply()
    }

    fun getTests(): List<Int> {
        val testsJson = preferences.getString(KEY_TESTS, null)
        return if (testsJson.isNullOrEmpty()) listOf(0, 0) else testsJsonAdapter.fromJson(testsJson)
            ?: listOf(0, 0)
    }
}
