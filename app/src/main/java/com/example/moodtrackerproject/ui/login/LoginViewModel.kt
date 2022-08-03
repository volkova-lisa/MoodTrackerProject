package com.example.moodtrackerproject.ui.login

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.login.LoginProps.LoginAction
import com.example.moodtrackerproject.ui.login.LoginProps.LoginError
import com.example.moodtrackerproject.utils.PreferenceManager
import com.example.moodtrackerproject.utils.isEmailValid
import com.example.moodtrackerproject.utils.isPasswordValid
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : BaseViewModel<LoginProps>() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private val props = LoginProps(
        checkLogInData = ::checkLogInData,
        action = null,
        isLoading = false,
        error = null,
        openRegistration = ::openRegistration,
        openResetPassword = ::openResetPassword,
        saveName = ::saveName
    )

    init {
        liveData.value = props
    }

    private fun checkLogInData(email: String, password: String) {
        when {
            email.isEmailValid() && password.isPasswordValid() -> {
                loginUserWithEmailAndPassword(email, password)
            }
            !email.isEmailValid() ->
                liveData.value =
                    props.copy(error = LoginError.ShowEmailInvalid)
            !password.isPasswordValid() ->
                liveData.value =
                    props.copy(error = LoginError.ShowPasswordInvalid)
            !email.isEmailValid() && !password.isPasswordValid() -> {
                liveData.value = props.copy(error = LoginError.ShowEmailInvalid)
                liveData.value = props.copy(error = LoginError.ShowPasswordInvalid)
            }
        }
    }

    private fun saveName() {
        Store.setState(Store.appState.homeState.copy(name = DataBaseRepository.getName()))
    }

    private fun openRegistration() {
        liveData.value = props.copy(action = LoginAction.StartRegistrationScreen)
    }

    private fun openResetPassword() {
        liveData.value = props.copy(action = LoginAction.StartResetPasswordScreen)
    }

    private fun loginUserWithEmailAndPassword(email: String, password: String) {
        liveData.value = props.copy(isLoading = true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                liveData.value = props.copy(isLoading = false)
                if (it.isSuccessful) {
                    liveData.value = props.copy(action = LoginAction.StartNotesScreen)
                    PreferenceManager.setInitUser(true)
                } else {
                    liveData.value = props.copy(error = LoginError.ShowNoInternet)
                }
            }
    }

    override fun map(appState: AppState, action: MviAction?): LoginProps {
        TODO("Not yet implemented")
    }
}
