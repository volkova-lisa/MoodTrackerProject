package com.example.moodtrackerproject.utils

import android.util.Patterns
import androidx.fragment.app.Fragment

fun Fragment.ifNetworkIsAvailable(): Boolean {
    TODO("Check if internet works")
    return true
}

fun String.isNameValid() = isNotEmpty()

fun String.isEmailValid() =
    isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isPasswordValid(): Boolean {
    val MIN_PASSWORD_LENGTH = 6
    return length >= MIN_PASSWORD_LENGTH && isNotEmpty()
}
