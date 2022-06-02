package com.example.moodtrackerproject.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.home.HomeAction.LogOut
import com.example.moodtrackerproject.ui.notes.Store
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel() : ViewModel() {
    private val state = HomeViewState()
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private val _homeStateLiveData: MutableLiveData<HomeViewState> =
        MutableLiveData<HomeViewState>().apply {
            value = state
        }
    val liveData get() = _homeStateLiveData

    fun fetchLists() {
        val moods = DataBaseRepository.getMoods()
        val notes = DataBaseRepository.getNotes()
        setState(
            state.copy(
                listOfMoods = moods,
                listOfNotes = notes
            )
        )
    }

    fun logOut() {
        liveData.value = state.copy(isLoggedIn = false)
        auth.signOut()
        liveData.value = state.copy(action = LogOut)
    }

    private fun setState(newState: HomeViewState) {
        Store.setState(newState)
        _homeStateLiveData.value = Store.appState.homeState
    }
}
