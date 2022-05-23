package com.example.moodtrackerproject.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<PROPS> : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext get() = Dispatchers.Main

    val liveData: MutableLiveData<PROPS> = MutableLiveData()
}
