package com.example.moodtrackerproject.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel

internal class LoginFragmentViewModel(app: Application) : AndroidViewModel(app) {
    private val mContext = app

    fun initDataBase(onSuccess: () -> Unit) {
    }
}
