package com.example.moodtrackerproject.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.utils.PreferenceManager
import com.example.moodtrackerproject.utils.isEmailValid
import com.example.moodtrackerproject.utils.isPasswordValid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class LoginViewModel() : ViewModel() {
    private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private var databaseReference: DatabaseReference? = null

    private val state = LoginViewState()

    private val _loginStateLiveData: MutableLiveData<LoginViewState> =
        MutableLiveData<LoginViewState>().apply {
            value = state
        }
    val liveData get() = _loginStateLiveData

    fun checkLogInData(email: String, password: String) {
        when {
            email.isEmailValid() && password.isPasswordValid() -> {
                loginUserWithEmailAndPassword(email, password)
            }
            !email.isEmailValid() -> liveData.value = state.copy(error = LoginError.ShowEmailInvalid)
            !password.isPasswordValid() -> liveData.value = state.copy(error = LoginError.ShowPasswordInvalid)
            !email.isEmailValid() && !password.isPasswordValid() ->
                {
                    liveData.value = state.copy(error = LoginError.ShowEmailInvalid)
                    liveData.value = state.copy(error = LoginError.ShowPasswordInvalid)
                }
        }
    }

    private fun loginUserWithEmailAndPassword(email: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            liveData.value = state.copy(isLoading = true)
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    liveData.value = state.copy(isLoading = false)
                    if (it.isSuccessful) {
                        liveData.value = state.copy(action = LoginAction.StartNotesScreen)
                        PreferenceManager.setInitUser(true)
                    } else {
                        // Toast.makeText(context.applicationContext, context.getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
                        // liveData.value = state.copy(error = LoginError.ShowLoginError)
                    }
                }
        }
    }
}
