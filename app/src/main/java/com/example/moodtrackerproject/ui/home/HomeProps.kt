package com.example.moodtrackerproject.ui.home

import com.example.moodtrackerproject.ui.mood.list.MoodProps

data class HomeProps(
    val isLoggedIn: Boolean = false,
    val logout: () -> Unit,
    val action: HomeAction? = null,
    val listOfMoodsToday: List<MoodProps.MoodItemProps> = listOf(),

) {
    sealed class HomeAction {
        object LogOut : HomeAction()
    }
}
