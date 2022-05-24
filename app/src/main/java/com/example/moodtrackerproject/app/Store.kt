package com.example.moodtrackerproject.app

import com.example.moodtrackerproject.app.mood.AddMoodState
import com.example.moodtrackerproject.app.mood.MoodState
import com.example.moodtrackerproject.app.notes.NoteDetailsState
import com.example.moodtrackerproject.app.notes.NotesState
import com.example.moodtrackerproject.app.tests.StressTestState

object Store {
    var appState = AppState()

    fun setState(newState: NotesState) {
        appState = appState.copy(notesState = newState)
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

    fun setState(newState: MoodState) {
        appState = appState.copy(moodState = newState)
    }
}
