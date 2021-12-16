package com.example.moodtrackerproject.ui.login

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentLoginScreenBinding
import com.example.moodtrackerproject.routing.Routes
import com.example.moodtrackerproject.ui.NotesFragment
import com.example.moodtrackerproject.utils.isEmailValid
import com.example.moodtrackerproject.utils.isPasswordValid
import com.google.firebase.auth.FirebaseAuth

internal class LoginViewModel() : ViewModel() {
    private lateinit var auth: FirebaseAuth

    private val _uiStateLiveData = MutableLiveData<LoginViewState>().apply {
        value = LoginViewState()
    }
    val uiStateLiveData: LiveData<LoginViewState> = _uiStateLiveData

    fun checkLogInData(email: String, password: String, binding: FragmentLoginScreenBinding, context: Context) {
        var isValid = true
        if (!email.isEmpty() && !email.isEmailValid()) {
            binding.emailInput.error = context.getString(R.string.registration_enter_email)
            isValid = false
        }
        if (!password.isEmpty() && !password.isPasswordValid()) {
            binding.passInput.error = context.getString(R.string.registration_enter_pass)
            isValid = false
        }
    }

    fun commitLogIn(binding: FragmentLoginScreenBinding, fragmentActivity: FragmentActivity, context: Context) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(binding.emailInput.text.toString(), binding.passInput.text.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Routes.goTo(fragmentActivity, NotesFragment())
                } else Toast.makeText(context.applicationContext, context.getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
            }
    }

    data class LoginViewState(
        var IS_LOADING: Boolean = false,
        var ERROR: String = "Error occurred",
        var SUCCESS: String = "Success"
    )
}
