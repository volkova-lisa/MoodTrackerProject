package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.app.mood.AddMoodState
import com.example.moodtrackerproject.app.mood.MoodState
import com.example.moodtrackerproject.app.notes.NoteDetailsState
import com.example.moodtrackerproject.app.notes.NotesState
import com.example.moodtrackerproject.app.tests.StressTestState
import com.example.moodtrackerproject.app.tests.TestResultsState

object Store {
    var appState = AppState()

    fun setState(newState: RegistrationState) {
        appState = appState.copy(registrationState = newState)
    }

    fun setState(newState: SettingsState) {
        appState = appState.copy(settingsState = newState)
    }

    fun setState(newState: NotesState) {
        appState = appState.copy(notesState = newState)
    }

    fun setState(newState: HomeState) {
        appState = appState.copy(homeState = newState)
    }

    fun setState(newState: AddMoodState) {
        appState = appState.copy(addMoodState = newState)
    }

    fun setState(newState: NoteDetailsState) {
        appState = appState.copy(noteDetailsState = newState)
    }

    fun setState(newState: StressTestState) {
        appState = appState.copy(stressTestState = newState)
    }

    fun setState(newState: EditHealthState) {
        appState = appState.copy(editHealthState = newState)
    }

    fun setState(newState: HealthState) {
        appState = appState.copy(healthState = newState)
    }

    fun setState(newState: MoodState) {
        appState = appState.copy(moodState = newState)
    }

    fun setState(newState: TestResultsState) {
        appState = appState.copy(testResultsState = newState)
    }
}
