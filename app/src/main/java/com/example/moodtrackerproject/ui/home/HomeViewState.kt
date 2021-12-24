package com.example.moodtrackerproject.ui.home

data class HomeViewState(
    val isLoggedIn: Boolean = false,
    val action: HomeAction? = null
)

sealed class HomeAction {
    object LogOut : HomeAction()
}
