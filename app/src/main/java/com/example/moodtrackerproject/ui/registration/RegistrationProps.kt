package com.example.moodtrackerproject.ui.registration

import com.example.moodtrackerproject.app.MviAction

data class RegistrationProps(
    val isLoading: Boolean = false,
    val checkRegistrationData: (String, String, String) -> Unit,
    val openLogin: () -> Unit,
    val action: RegistrationAction? = null,
    val error: RegistrationError? = null,
    val saveAccData: (String, String) -> Unit,
    val fetchName: () -> Unit = {}
) {
    sealed class RegistrationAction : MviAction {
        object StartLogInScreen : RegistrationAction()
    }

    sealed class RegistrationError {
        object ShowRegistrationError : RegistrationError()
        object ShowPasswordInvalid : RegistrationError()
        object ShowEmailInvalid : RegistrationError()
        object ShowNameInvalid : RegistrationError()
    }
}
