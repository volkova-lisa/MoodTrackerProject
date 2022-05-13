package com.example.moodtrackerproject.ui.notes

import com.example.moodtrackerproject.ui.mood.add.AddMoodViewState
import com.example.moodtrackerproject.ui.mood.list.MoodViewState
import com.example.moodtrackerproject.ui.mood.tests.StressTestState
import com.example.moodtrackerproject.ui.notes.details.DetailsViewState
import com.example.moodtrackerproject.ui.notes.list.NotesViewState

object Store {
    var appState = AppState()

    fun setState(newState: NotesViewState) {
        appState = appState.copy(notesState = newState)
    }

    fun setState(newState: AddMoodViewState) {
        appState = appState.copy(addMoodState = newState)
    }

    fun getNotesState(): NotesViewState {
        return appState.notesState
    }

    // TODO: refactor
    fun saveEdited(title: String, text: String) {
        appState = appState.copy(
            notesState = appState.notesState.copy(
                textEdited = true,
                listOfNotes = listOf(
                    appState.notesState.listOfNotes[0].copy(
                        title = title,
                        text = text,
                        noteId = appState.notesState.currentId
                    )
                )
            )
        )
    }

    fun setState(newState: DetailsViewState) {
        appState = appState.copy(noteDetailsState = newState)
    }
    fun setState(newState: StressTestState) {
        appState = appState.copy(stressTestState = newState)
    }

    fun setState(newState: MoodViewState) {
        appState = appState.copy(moodState = newState)
    }
}

data class AppState(
    val notesState: NotesViewState = NotesViewState(),
    val noteDetailsState: DetailsViewState = DetailsViewState(),
    val addMoodState: AddMoodViewState = AddMoodViewState(),
    val moodState: MoodViewState = MoodViewState(),
    val stressTestState: StressTestState = StressTestState()
)
