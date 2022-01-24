package com.example.moodtrackerproject.ui.notes

import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.details.DetailsViewState
import com.example.moodtrackerproject.ui.notes.list.NotesViewState

object Store {
    var appState = AppState()

    fun setState(newState: NotesViewState) {
        appState = appState.copy(notesState = newState)
    }

    fun getNotesId(): String {
        return appState.notesState.currentId
    }

    fun setState(newState: DetailsViewState) {
        appState = appState.copy(noteDetailsState = newState)
    }
}

data class AppState(
    val notesState: NotesViewState = NotesViewState(0, false, null, {}, {}, "", listOf()),
    val noteDetailsState: DetailsViewState = DetailsViewState({}, {}, {}, "", NoteBody(), "", "", {}, {}, null)
)

// в NotesViewModel
// privat val state: NotesViewState by lazy {
//    Store.appState.notesState
