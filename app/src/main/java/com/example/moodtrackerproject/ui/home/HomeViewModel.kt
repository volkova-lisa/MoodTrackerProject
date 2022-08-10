package com.example.moodtrackerproject.ui.home

import android.util.Log
import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.HomeState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.app.Store.appState
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.MaxHealthModel
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.home.HomeProps.HomeAction
import com.example.moodtrackerproject.ui.mood.list.MoodProps
import com.example.moodtrackerproject.ui.notes.list.NotesListProps
import com.example.moodtrackerproject.utils.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : BaseViewModel<HomeProps>() {

    private val props = HomeProps(
        action = null,
        logout = ::logOut,
        fetchListOfMoods = ::fetchListOfMoods,
        fetchListOfNotes = ::fetchListOfNotes,
        fetchHealth = ::fetchListOfHealth,
        fetchResults = ::fetchResults,
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
                    moodId = it.moodId,
                    isDeleted = it.isDeleted,
                    deleteMood = { deleted ->
                        DataBaseRepository.setMoodDeleted(it)
                        val list = DataBaseRepository.removeDeletedMood()
                        setState(state.copy(listOfMoods = list))
                    }
                )
            },
            listOfNotesToday = state.listOfNotes.map {
                NotesListProps.NoteItemProps(
                    noteId = it.noteId,
                    date = it.date,
                    editedDate = it.editDate,
                    title = it.title,
                    text = it.text,
                    isChecked = it.isChecked,
                    isDeleted = it.isDeleted,
                    checkChanged = {},
                    openDetails = {},
                    deleteNote = {}
                )
            },
            isLoggedIn = state.isLoggedIn,
            logout = ::logOut,
            healthItems =
            if (state.healthModel != null) {
                HomeProps.HomeItemProps(
                    water = state.healthModel.water,
                    steps = state.healthModel.steps,
                    sleep = state.healthModel.sleep,
                    kcal = state.healthModel.kcal,
                )
            } else null,
            fetchListOfMoods = ::fetchListOfMoods,
            fetchListOfNotes = ::fetchListOfNotes,
            fetchHealth = ::fetchListOfHealth,
            testResults = state.resultModel,
            fetchResults = ::fetchResults,
            fetchName = ::fetchName,
            name = state.name,
            email = state.email,
            healthMax = state.healthMax,
        )
    }

    private fun fetchName() {
        launch {
            val name = withContext(Dispatchers.IO) {
                DataBaseRepository.getName()
            }
            setState(appState.homeState.copy(name = name))
        }
    }

    private fun fetchListOfHealth() {
        launch {
            val listHealth = withContext(Dispatchers.IO) {
                DataBaseRepository.getHealth()
            }
            setState(appState.homeState.copy(healthModel = listHealth))
            val maxHealth = withContext(Dispatchers.IO) {
                DataBaseRepository.getHealthMax()
            }
            setState(
                Store.appState.homeState.copy(
                    healthMax =
                    MaxHealthModel(
                        waterMax = maxHealth.waterMax,
                        stepsMax = maxHealth.stepsMax,
                        sleepMax = maxHealth.sleepMax,
                        kcalMax = maxHealth.kcalMax
                    )
                )
            )
        }
    }

    private fun fetchResults() {
        launch {
            val results = withContext(Dispatchers.IO) {
                DataBaseRepository.getTestResults()
            }
            setState(appState.homeState.copy(resultModel = results))
        }
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
            liveData.value = props.copy(action = null)
        }
    }

    private fun fetchListOfNotes() {
        launch {
            val notes = withContext(Dispatchers.IO) {
                DataBaseRepository.getNotes()
            }
            setState(appState.homeState.copy(listOfNotes = notes))
        }
    }

    private fun setState(state: HomeState) {
        setState(appState.copy(homeState = state))
    }
}
