package com.example.moodtrackerproject.ui.home

data class HomeProps(
    val isLoggedIn: Boolean = false,
    val logout: () -> Unit,
    val action: HomeAction? = null
) {
    sealed class HomeAction {
        object LogOut : HomeAction()
    }
}
