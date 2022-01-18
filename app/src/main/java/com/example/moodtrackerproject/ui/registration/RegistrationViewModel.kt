package com.example.moodtrackerproject.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.utils.NAME
import com.example.moodtrackerproject.utils.PROFILE
import com.example.moodtrackerproject.utils.isEmailValid
import com.example.moodtrackerproject.utils.isPasswordValid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {

    private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private var databaseReference: DatabaseReference? = null

    private val state = RegistrationViewState()

    private val _registrationStateLiveData: MutableLiveData<RegistrationViewState> =
        MutableLiveData<RegistrationViewState>().apply {
            value = state
        }
    val liveData get() = _registrationStateLiveData

    fun checkRegistrationData(
        email: String,
        password: String,
        fullName: String
    ) {
        when {
            fullName.isNotEmpty() && email.isEmailValid() && password.isPasswordValid() -> {
                registerUserWithEmailAndPassword(fullName, email, password)
            }
            fullName.isEmpty() ->
                liveData.value =
                    state.copy(error = RegistrationError.ShowNameInvalid)
            !email.isEmailValid() || email.isEmpty() ->
                liveData.value =
                    state.copy(error = RegistrationError.ShowEmailInvalid)
            !password.isPasswordValid() || password.isEmpty() ->
                liveData.value =
                    state.copy(error = RegistrationError.ShowPasswordInvalid)
        }
    }

    private fun registerUserWithEmailAndPassword(name: String, email: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            databaseReference = database.reference.child(PROFILE)
            liveData.value = state.copy(isLoading = true)

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    liveData.value = state.copy(isLoading = false)
                    if (it.isSuccessful) {
                        val currentUser = auth.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child(NAME)?.setValue(name)
                        liveData.value =
                            state.copy(action = RegistrationAction.StartLogInScreen)
                    } else {
                        liveData.value =
                            state.copy(error = RegistrationError.ShowRegistrationError)
                    }
                }
        }
    }
}
