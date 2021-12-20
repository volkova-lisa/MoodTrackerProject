package com.example.moodtrackerproject.ui.login

import java.lang.Exception

data class LoginViewState(
    val isLoading: Boolean = false,
    val action: LoginAction? = null,
    val error: LoginError? = null,
)

sealed class LoginAction {
    object StartNotesScreen : LoginAction()
    object StartRegistrationScreen : LoginAction()
    object StartResetPasswordScreen : LoginAction()
}

sealed class LoginError {
    object ShowNoInternet : LoginError()
    object ShowPasswordInvalid : LoginError()
    object ShowEmailInvalid : LoginError()
}
