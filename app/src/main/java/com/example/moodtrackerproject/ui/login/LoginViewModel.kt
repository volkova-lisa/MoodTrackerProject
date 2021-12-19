package com.example.moodtrackerproject.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.utils.isEmailValid
import com.example.moodtrackerproject.utils.isPasswordValid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception
class LoginViewModel() : ViewModel() {

    private val _loginOutputLiveData: MutableLiveData<LoginOutputs> =
        MutableLiveData()
    val loginOutputLiveData get() = _loginOutputLiveData

    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    private lateinit var auth: FirebaseAuth

    private val _loginStateLiveData: MutableLiveData<LoginViewState> =
        MutableLiveData<LoginViewState>().apply {
            value = LoginViewState()
        }
    val loginStateLiveData get() = _loginStateLiveData

    fun checkLogInData(email: String, password: String) {
        var isValid = true

        if (!email.isEmailValid()) {
            setLoginOutput(LoginOutputs.ShowEmailInvalid)
            isValid = false
        }
        if (!password.isPasswordValid()) {
            setLoginOutput(LoginOutputs.ShowPasswordInvalid)
            isValid = false
        }

        if (isValid) {
            return loginUserWithEmailAndPassword(email, password)
        } else {
            setLoginOutput(LoginOutputs.ShowNoInternet)
        }
    }

    private fun loginUserWithEmailAndPassword(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Routes.goTo(fragmentActivity, NotesFragment())
                } else {
                    // Toast.makeText(context.applicationContext, context.getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun setLoginOutput(loginOutput: LoginOutputs) {
        loginOutputLiveData.value = loginOutput
    }
}

data class LoginViewState(var isLoading: Boolean = false)

sealed class LoginOutputs {
    class ShowLoginError(val exception: Exception) : LoginOutputs()
    object StartNotesScreen : LoginOutputs()
    object StartLogInScreen : LoginOutputs()
    object StartResetPasswordScreen : LoginOutputs()
    object ShowNoInternet : LoginOutputs()
    object ShowPasswordInvalid : LoginOutputs()
    object ShowEmailInvalid : LoginOutputs()
}
