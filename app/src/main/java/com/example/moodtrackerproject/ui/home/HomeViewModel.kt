package com.example.moodtrackerproject.ui.home

import android.util.Log
import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.HomeState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.app.Store.appState
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.home.HomeProps.HomeAction
import com.example.moodtrackerproject.ui.mood.list.MoodProps
import com.example.moodtrackerproject.utils.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : BaseViewModel<HomeProps>() {

    private val props = HomeProps(
        action = null,
        logout = ::logOut,
        fetchListOfMoods = ::fetchListOfMoods
    )

    init {
        setState(Store.appState.homeState)
        liveData.value = props
    }

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun map(appState: AppState, action: MviAction?): HomeProps {
        val state = appState.homeState
        return HomeProps(
            action = action as? HomeAction,
            listOfMoodsToday = state.listOfMoods.map {
                MoodProps.MoodItemProps(
                    emojiSrc = it.emojiSrc,
                    moodTitle = it.moodTitle,
                    moodTime = it.moodTime,
                )
            },
            isLoggedIn = state.isLoggedIn,
            logout = ::logOut,
            fetchListOfMoods = ::fetchListOfMoods
        )
        Log.d("88888888", props.listOfMoodsToday.toString())
    }

    private fun logOut() {
        liveData.value = props.copy(isLoggedIn = false)
        auth.signOut()
        PreferenceManager.setInitUser(false)
        liveData.value = props.copy(action = HomeAction.LogOut)
    }

    private fun fetchListOfMoods() {
        launch {
            val moods = withContext(Dispatchers.IO) {
                DataBaseRepository.getMoods()
            }
            setState(Store.appState.homeState.copy(listOfMoods = moods))
        }
    }

    private fun setState(state: HomeState) {
        setState(Store.appState.copy(homeState = state))
    }
}
