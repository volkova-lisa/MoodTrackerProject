package com.example.moodtrackerproject.ui.welcome

data class WelcomeProps(
    val openHome: () -> Unit,
    val action: WelcomeAction? = null,
)

sealed class WelcomeAction {
    object StartHomesScreen : WelcomeAction()
}
