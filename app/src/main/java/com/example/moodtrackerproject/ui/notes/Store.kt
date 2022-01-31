package com.example.moodtrackerproject.ui.notes

import com.example.moodtrackerproject.ui.notes.details.DetailsViewState
import com.example.moodtrackerproject.ui.notes.list.NoteBodyUiModel
import com.example.moodtrackerproject.ui.notes.list.NotesViewState

object Store {
    var appState = AppState()

    fun setState(newState: NotesViewState) {
        appState = appState.copy(notesState = newState)
    }

    fun getState(): NotesViewState {
        return appState.notesState
    }

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
}

data class AppState(
    val notesState: NotesViewState = NotesViewState(0, false, null, {}, {}, "", listOf(), false),
    val noteDetailsState: DetailsViewState = DetailsViewState(
        {}, {}, {}, NoteBodyUiModel(), {}, null
    )
)
