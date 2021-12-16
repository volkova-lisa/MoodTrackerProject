package com.example.moodtrackerproject.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentLoginScreenBinding
import com.example.moodtrackerproject.utils.isEmailValid
import com.example.moodtrackerproject.utils.isPasswordValid
import com.google.firebase.auth.FirebaseAuth

internal class LoginViewModel() : ViewModel() {
    lateinit var auth: FirebaseAuth

    fun initDataBase(onSuccess: () -> Unit) {
    }

    fun checkLogInData(email: String, password: String, binding: FragmentLoginScreenBinding, context: Context) {
        var isValid = true
        if (!email.isEmpty()) {
            if (!email.isEmailValid()) {
                binding.emailInput.error = context.getString(R.string.registration_enter_email)
                isValid = false
            } else {
            }
        }
        if (!password.isEmpty()) {
            if (!password.isPasswordValid()) {
                binding.passInput.error = context.getString(R.string.registration_enter_pass)
                isValid = false
            } else {
            }
        }
    }
}
