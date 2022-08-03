package com.example.moodtrackerproject

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.data.DataBaseRepository
import com.yariksoffice.lingver.Lingver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun fetchData() {
        viewModelScope.launch(Dispatchers.Main) {
            val darkMode = DataBaseRepository.getMode()
            if (darkMode) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            val lang = Lingver.getInstance().getLanguage()
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
                    language = lang,
                    name = name,
                    photo = photo,
                    isDarkOn = darkMode,
                )
            )
        }
    }
}
