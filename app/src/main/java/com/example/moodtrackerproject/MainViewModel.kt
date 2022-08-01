package com.example.moodtrackerproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.data.DataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun fetchData() {
        viewModelScope.launch(Dispatchers.Main) {
            val photo = DataBaseRepository.getPhoto()
            val name = DataBaseRepository.getName()
            Store.setState(
                Store.appState.homeState.copy(
                    photo = photo,
                    name = name,
                )
            )

            Store.setState(
                Store.appState.settingsState.copy(
                    language = DataBaseRepository.getLang(),
                    name = name,
                    photo = photo
                )
            )
        }
    }
}
