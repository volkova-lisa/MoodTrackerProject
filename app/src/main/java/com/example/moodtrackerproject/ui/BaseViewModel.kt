package com.example.moodtrackerproject.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.app.Store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<PROPS> : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext get() = Dispatchers.Main

    val liveData: MutableLiveData<PROPS> = MutableLiveData()

    fun setState(appState: AppState, action: MviAction? = null) {
        Store.appState = appState
        liveData.value = map(appState, action)
    }

    abstract fun map(appState: AppState, action: MviAction?): PROPS
}
