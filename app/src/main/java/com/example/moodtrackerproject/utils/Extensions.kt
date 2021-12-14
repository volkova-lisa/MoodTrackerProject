package com.example.moodtrackerproject.utils

import android.util.Patterns

fun String.isEmailValid() =
    Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isPasswordValid(): Boolean {
    val MIN_PASSWORD_LENGTH = 6
    return length >= MIN_PASSWORD_LENGTH
}
