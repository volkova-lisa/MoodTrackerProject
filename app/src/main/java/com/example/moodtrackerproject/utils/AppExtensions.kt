package com.example.moodtrackerproject.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.SystemClock
import android.util.Base64
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.moodtrackerproject.R
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

private const val MIN_PASSWORD_LENGTH = 6

val Context.inflater: LayoutInflater get() = LayoutInflater.from(this)

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true && capabilities.hasCapability(
        NetworkCapabilities.NET_CAPABILITY_VALIDATED
    )
}

fun Context.hideKeyboard(view: View?) {
    if (view != null) {
        val inputMethodManager: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Fragment.hideKeyboard(view: View?) {
    activity?.hideKeyboard(view)
}

fun Fragment.hideKeyboard() {
    hideKeyboard(view)
}

fun Activity.snackBar(text: String) {
    Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
        .show()
}

fun String.isEmailValid() =
    isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isPasswordValid(): Boolean {
    return length >= MIN_PASSWORD_LENGTH && isNotEmpty()
}

fun View.click(action: () -> Unit, delay: Int = 300) {
    setOnClickListener(
        object : View.OnClickListener {
            private var lastClickTime: Long = 0

            override fun onClick(v: View) {
                if (SystemClock.elapsedRealtime() - lastClickTime < delay) return
                else action()
                lastClickTime = SystemClock.elapsedRealtime()
            }
        }
    )
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.visibleIf(condition: Boolean?) {
    if (condition == true) {
        this.makeVisible()
    } else {
        this.makeGone()
    }
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone? = null): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.ENGLISH)
    formatter.timeZone = timeZone ?: TimeZone.getDefault()
    return formatter.format(this)
}

fun Bitmap.convertToString(): String {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, baos)
    val b = baos.toByteArray()
    val encoded = Base64.encodeToString(b, Base64.NO_WRAP)
    return encoded
}

fun String.convertToBitmap(res: Resources): Bitmap {
    val imageAsBytes: ByteArray = Base64.decode(this, Base64.NO_WRAP)

    return if (this.isEmpty()) {
        BitmapFactory.decodeResource(res, R.drawable.anon)
    } else {
        BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
    }
}
