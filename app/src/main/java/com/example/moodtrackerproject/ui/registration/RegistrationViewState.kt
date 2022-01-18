package com.example.moodtrackerproject.ui.registration

data class RegistrationViewState(
    val isLoading: Boolean = false,
    val action: RegistrationAction? = null,
    val error: RegistrationError? = null,
)

sealed class RegistrationAction {
    object StartLogInScreen : RegistrationAction()
}

sealed class RegistrationError {
    object ShowRegistrationError : RegistrationError()
    object ShowNoInternet : RegistrationError()
    object ShowPasswordInvalid : RegistrationError()
    object ShowEmailInvalid : RegistrationError()
    object ShowNameInvalid : RegistrationError()
}
