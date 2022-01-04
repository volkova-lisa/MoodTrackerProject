package com.example.moodtrackerproject.utils

import android.content.Context
import android.content.SharedPreferences




object Preference {
    private const val INIT_USER = "init_user"
    private const val PREF = "pref"
    private const val NOTES = "all_notes"
    private lateinit var preferences: SharedPreferences

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

    fun saveNotes(notesList: String) {
        preferences.edit()
            .putString(NOTES, notesList)
            .apply()
    }


    fun getInitUser(): Boolean {
        return preferences.getBoolean(INIT_USER, false)
    }
}
