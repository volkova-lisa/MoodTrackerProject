package com.example.moodtrackerproject.ui.login

data class LoginProps(
    val isLoading: Boolean = false,
    val openRegistration: () -> Unit,
    val openResetPassword: () -> Unit,
    val saveName: () -> Unit,
    val checkLogInData: (String, String) -> Unit,
    val action: LoginAction? = null,
    val error: LoginError? = null,
) {
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
}
