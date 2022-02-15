package com.example.moodtrackerproject.ui.mood.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoodViewModel : ViewModel() {

    private var state: MoodViewState

    init {
        state = MoodViewState(

        )
    }

    private val _moodViewStateLiveData: MutableLiveData<MoodViewState> =
        MutableLiveData<MoodViewState>().apply {
            value = state
        }
    val liveData get() = _moodViewStateLiveData
}