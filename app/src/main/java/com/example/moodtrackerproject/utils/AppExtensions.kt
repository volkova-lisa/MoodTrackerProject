package com.example.moodtrackerproject.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

val Context.inflater: LayoutInflater get() = LayoutInflater.from(this)

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
}

fun Context.hideKeyboard(view: View?) {
    if (view != null) {
        val inputMethodManager: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Activity.snackBar(text: String) {
    Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
        .show()
}

fun Fragment.ifNetworkIsAvailable(): Boolean {
    TODO("Check if internet works")
    return true
}

fun String.isEmailValid() =
    isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isPasswordValid(): Boolean {
    val MIN_PASSWORD_LENGTH = 6
    return length >= MIN_PASSWORD_LENGTH && isNotEmpty()
}
