package com.example.moodtrackerproject.ui.mood.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoodViewModel : ViewModel() {

    private var state: MoodViewState

    init {
        state = MoodViewState(
            addNewMood = ::addNewMood
        )
    }

    private fun addNewMood() {
        liveData.value = state.copy(action = MoodScreenActions.StartAddMoodScreen)
        Log.d("----------------", "CLICKED")
    }

    private val _moodViewStateLiveData: MutableLiveData<MoodViewState> =
        MutableLiveData<MoodViewState>().apply {
            value = state
        }
    val liveData get() = _moodViewStateLiveData
}
