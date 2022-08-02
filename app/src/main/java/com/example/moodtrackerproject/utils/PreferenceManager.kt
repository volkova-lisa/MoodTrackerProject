package com.example.moodtrackerproject.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.moodtrackerproject.domain.*
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
    private const val KEY_LANG = "all_lang"
    private const val KEY_MODE = "all_mode"
    private const val KEY_NAME = "all_name"
    private const val KEY_EMAIL = "all_email"
    private const val KEY_PHOTO = "all_photo"
    private const val KEY_MAX_H = "all_max"

    private lateinit var preferences: SharedPreferences

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val valuesNote: ParameterizedType =
        Types.newParameterizedType(List::class.java, NoteModel::class.java)

    private val valuesMood: ParameterizedType =
        Types.newParameterizedType(List::class.java, MoodModel::class.java)

    private val healthJsonAdapter: JsonAdapter<HealthModel> = moshi.adapter(HealthModel::class.java)
    private val testsJsonAdapter: JsonAdapter<ResultsModel> = moshi.adapter(ResultsModel::class.java)
    private val notesJsonAdapter: JsonAdapter<List<NoteModel>> = moshi.adapter(valuesNote)
    private val moodsJsonAdapter: JsonAdapter<List<MoodModel>> = moshi.adapter(valuesMood)
    private val languageJsonAdapter: JsonAdapter<String> = moshi.adapter(String::class.java)
    private val modeJsonAdapter: JsonAdapter<Boolean> = moshi.adapter(Boolean::class.java)
    private val nameJsonAdapter: JsonAdapter<String> = moshi.adapter(String::class.java)
    private val emailJsonAdapter: JsonAdapter<String> = moshi.adapter(String::class.java)
    private val photoJsonAdapter: JsonAdapter<String> = moshi.adapter(String::class.java)
    private val maxHealthJsonAdapter: JsonAdapter<MaxHealthModel> = moshi.adapter(MaxHealthModel::class.java)

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

    fun saveHealth(healthModel: HealthModel) {
        val serializedHealth = healthJsonAdapter.toJson(healthModel)
        preferences.edit()
            .putString(KEY_HEAlTH, serializedHealth)
            .apply()
    }

    fun getHealth(): HealthModel {
        val healthJson = preferences.getString(KEY_HEAlTH, null)
        return if (healthJson.isNullOrEmpty()) HealthModel() else healthJsonAdapter.fromJson(healthJson)
            ?: HealthModel()
    }

    fun saveTests(resultModel: ResultsModel) {
        val serializedTests = testsJsonAdapter.toJson(resultModel)
        preferences.edit()
            .putString(KEY_TESTS, serializedTests)
            .apply()
    }

    fun getTests(): ResultsModel {
        val testsJson = preferences.getString(KEY_TESTS, null)
        return if (testsJson.isNullOrEmpty()) ResultsModel() else testsJsonAdapter.fromJson(testsJson)
            ?: ResultsModel()
    }

    fun saveLanguage(lang: String) {
        val serializedLang = languageJsonAdapter.toJson(lang)
        preferences.edit()
            .putString(KEY_LANG, serializedLang)
            .apply()
    }

    fun getLanguage(): String {
        val testsJson = preferences.getString(KEY_LANG, null)
        return if (testsJson.isNullOrEmpty()) " " else languageJsonAdapter.fromJson(testsJson)
            ?: " "
    }

    fun saveLanguage(mode: Boolean) {
        val serializedMode = modeJsonAdapter.toJson(mode)
        preferences.edit()
            .putString(KEY_MODE, serializedMode)
            .apply()
    }

    fun getMode(): Boolean {
        val testsJson = preferences.getString(KEY_MODE, null)
        return if (testsJson.isNullOrEmpty()) false else modeJsonAdapter.fromJson(testsJson)
            ?: false
    }

    fun saveName(name: String) {
        val serializedName = nameJsonAdapter.toJson(name)
        preferences.edit()
            .putString(KEY_NAME, serializedName)
            .apply()
    }

    fun getName(): String {
        val testsJson = preferences.getString(KEY_NAME, null)
        return if (testsJson.isNullOrEmpty()) "No Name" else nameJsonAdapter.fromJson(testsJson)
            ?: "No Name"
    }

    fun saveEmail(email: String) {
        val serializedEmail = emailJsonAdapter.toJson(email)
        preferences.edit()
            .putString(KEY_EMAIL, serializedEmail)
            .apply()
    }
    fun getEmail(): String {
        val testsJson = preferences.getString(KEY_EMAIL, null)
        return if (testsJson.isNullOrEmpty()) "No Email" else emailJsonAdapter.fromJson(testsJson)
            ?: "No Email"
    }

    fun savePhoto(photo: String) {
        val serializedLang = photoJsonAdapter.toJson(photo)
        preferences.edit()
            .putString(KEY_PHOTO, serializedLang)
            .apply()
    }

    fun getPhoto(): String {
        return preferences.getString(KEY_PHOTO, "")!!
    }

    fun saveHealthMax(max: MaxHealthModel) {
        val serializedMax = maxHealthJsonAdapter.toJson(max)
        preferences.edit()
            .putString(KEY_MAX_H, serializedMax)
            .apply()
    }

    fun getHealthMax(): MaxHealthModel {
        val testsJson = preferences.getString(KEY_MAX_H, null)
        return if (testsJson.isNullOrEmpty()) MaxHealthModel(2000, 3000, 8, 2000) else maxHealthJsonAdapter.fromJson(testsJson)
            ?: MaxHealthModel(2000, 3000, 8, 2000)
    }
}
