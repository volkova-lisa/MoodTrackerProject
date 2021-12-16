package com.example.moodtrackerproject.ui.login

import android.content.Context
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentRegistrationBinding
import com.example.moodtrackerproject.utils.*

class RegistrationViewModel {

    fun checkRegistrationData(email: String, password: String, fullName: String, binding: FragmentRegistrationBinding, context: Context) {
        var isValid = true
        if (!fullName.isEmpty()) {
            binding.nameInput.error = context.getString(R.string.enter_name)
            isValid = false
        } else {
            FULL_NAME = fullName
        }

        if (!email.isEmpty()) {
            if (!email.isEmailValid()) {
                binding.emailInput.error = context.getString(R.string.registration_enter_email)
                isValid = false
            } else {
                EMAIL = email
            }
        }

        if (!password.isEmpty()) {
            if (!password.isPasswordValid()) {
                binding.passInput.error = context.getString(R.string.registration_enter_pass)
                isValid = false
            } else {
                PASSWORD = password
            }
        }
    }
}
