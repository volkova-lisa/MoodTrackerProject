package com.example.moodtrackerproject.ui.registration

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.registration.RegistrationProps.RegistrationAction
import com.example.moodtrackerproject.ui.registration.RegistrationProps.RegistrationError
import com.example.moodtrackerproject.utils.NAME
import com.example.moodtrackerproject.utils.PROFILE
import com.example.moodtrackerproject.utils.isEmailValid
import com.example.moodtrackerproject.utils.isPasswordValid
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationViewModel : BaseViewModel<RegistrationProps>() {

    private val props = RegistrationProps(
        action = null,
        checkRegistrationData = ::checkRegistrationData,
        openLogin = ::openLogin,
    )

    init {
        liveData.value = props
    }

    private val database: FirebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private var databaseReference: DatabaseReference? = null

    private fun checkRegistrationData(
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
                    props.copy(error = RegistrationError.ShowNameInvalid)
            !email.isEmailValid() || email.isEmpty() ->
                liveData.value =
                    props.copy(error = RegistrationError.ShowEmailInvalid)
            !password.isPasswordValid() || password.isEmpty() ->
                liveData.value =
                    props.copy(error = RegistrationError.ShowPasswordInvalid)
        }
    }

    private fun openLogin() {
        liveData.value = props.copy(action = RegistrationAction.StartLogInScreen)
    }

    private fun registerUserWithEmailAndPassword(name: String, email: String, password: String) {
        databaseReference = database.reference.child(PROFILE)
        liveData.value = props.copy(isLoading = true)

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                liveData.value = props.copy(isLoading = false)
                if (it.isSuccessful) {
                    val currentUser = auth.currentUser
                    val currentUserDb = databaseReference?.child(currentUser?.uid!!)
                    currentUserDb?.child(NAME)?.setValue(name)
                    liveData.value =
                        props.copy(action = RegistrationAction.StartLogInScreen)
                } else {
                    liveData.value =
                        props.copy(error = RegistrationError.ShowRegistrationError)
                }
            }
    }

    override fun map(appState: AppState, action: MviAction?): RegistrationProps {
        TODO("Not yet implemented")
    }
}
