package com.example.moodtrackerproject.ui.mood.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.notes.Store

class MoodViewModel : ViewModel() {

    private var state: MoodViewState

    init {
        state = MoodViewState(
            addNewMood = ::addNewMood
        )
        Store.setState(state)
    }

    private val _moodViewStateLiveData: MutableLiveData<MoodViewState> =
        MutableLiveData<MoodViewState>().apply {
            value = state
        }
    val liveData get() = _moodViewStateLiveData

    private fun addNewMood() {
        liveData.value = state.copy(action = MoodScreenActions.StartAddMoodScreen)
    }

    fun fetchListOfMoods() {
        val moods = DataBaseRepository.getMoods()
        setState(state.copy(listOfMoods = moods))
    }
    private fun setState(newState: MoodViewState) {
        Store.setState(newState)
        _moodViewStateLiveData.value = Store.appState.moodState
    }
}
