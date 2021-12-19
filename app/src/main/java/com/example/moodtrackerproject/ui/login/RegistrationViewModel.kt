package com.example.moodtrackerproject.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class RegistrationViewModel : ViewModel() {

    private val _registrationOutputLiveData: MutableLiveData<RegistrationOutputs> =
        MutableLiveData()
    val registrationOutputLiveData get() = _registrationOutputLiveData

    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    private lateinit var auth: FirebaseAuth

    private val _registrationStateLiveData: MutableLiveData<RegistrationViewState> =
        MutableLiveData<RegistrationViewState>().apply {
            value = RegistrationViewState()
        }
    val registrationStateLiveData get() = _registrationStateLiveData

    fun checkRegistrationData(
        email: String,
        password: String,
        fullName: String
    ) {
        var isValid = true

        if (!fullName.isNameValid()) {
            setRegistrationOutput(ShowNameInvalid)
            isValid = false
        }
        if (!email.isEmailValid()) {
            setRegistrationOutput(ShowEmailInvalid)
            isValid = false
        }
        if (!password.isPasswordValid()) {
            setRegistrationOutput(ShowPasswordInvalid)
            isValid = false
        }

        if (isValid) {
            return registerUserWithEmailAndPassword(fullName, email, password)
        } else {
            setRegistrationOutput(ShowNoInternet)
        }
    }

    private fun registerUserWithEmailAndPassword(name: String, email: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            auth = FirebaseAuth.getInstance()
            database = FirebaseDatabase.getInstance()
            databaseReference = database?.reference!!.child(PROFILE)

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val currentUser = auth.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child(NAME)?.setValue(name)
                        // toast successful
                        // Routes.goTo() login
                    } else {
                        // toast failed
                    }
                }
        }
    }

    fun setRegistrationOutput(registrationOutput: RegistrationOutputs) {
        registrationOutputLiveData.value = registrationOutput
    }
}

data class RegistrationViewState(var isLoading: Boolean = false)

sealed class RegistrationOutputs
class ShowRegistrationError(val exception: Exception) : RegistrationOutputs()
object StartLogInScreen : RegistrationOutputs()
object StartResetPasswordScreen : RegistrationOutputs()
object ShowNoInternet : RegistrationOutputs()
object ShowPasswordInvalid : RegistrationOutputs()
object ShowEmailInvalid : RegistrationOutputs()
object ShowNameInvalid : RegistrationOutputs()
