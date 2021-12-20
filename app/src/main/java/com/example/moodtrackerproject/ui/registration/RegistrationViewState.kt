package com.example.moodtrackerproject.ui.registration

import java.lang.Exception

data class RegistrationViewState(
    val isLoading: Boolean = false,
    val action: RegistrationAction? = null,
    val error: RegistrationError? = null,
)

sealed class RegistrationAction {
    object StartLogInScreen : RegistrationAction()
    object StartResetPasswordScreen : RegistrationAction()
}

sealed class RegistrationError {
    class ShowRegistrationError(val exception: Exception) : RegistrationError()
    object ShowNoInternet : RegistrationError()
    object ShowPasswordInvalid : RegistrationError()
    object ShowEmailInvalid : RegistrationError()
    object ShowNameInvalid : RegistrationError()
}
