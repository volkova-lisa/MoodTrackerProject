package com.example.moodtrackerproject.utils

import android.content.Context
import android.content.SharedPreferences

object Preference {
    private const val INIT_USER = "init_user"
    private const val PREF = "pref"
    private lateinit var preferences: SharedPreferences

    fun getPreference(context: Context): SharedPreferences {
        preferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return preferences
    }

    fun setInitUser(init: Boolean) {
        preferences.edit()
            .putBoolean(INIT_USER, init)
            .apply()
    }

//    fun getInitUser(): Boolean {
//        return preferences.getBoolean(INIT_USER, false)
//    }
}
