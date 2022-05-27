package com.example.moodtrackerproject.ui.registration

data class RegistrationProps(
    val isLoading: Boolean = false,
    val checkRegistrationData: (String, String, String) -> Unit,
    val openLogin: () -> Unit,
    val action: RegistrationAction? = null,
    val error: RegistrationError? = null,
) {
    sealed class RegistrationAction {
        object StartLogInScreen : RegistrationAction()
    }

    sealed class RegistrationError {
        object ShowRegistrationError : RegistrationError()
        object ShowPasswordInvalid : RegistrationError()
        object ShowEmailInvalid : RegistrationError()
        object ShowNameInvalid : RegistrationError()
    }
}
