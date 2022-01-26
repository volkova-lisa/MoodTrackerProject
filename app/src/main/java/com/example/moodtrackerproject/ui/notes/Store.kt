package com.example.moodtrackerproject.ui.notes

import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.details.DetailsViewState
import com.example.moodtrackerproject.ui.notes.list.NoteBodyUiModel
import com.example.moodtrackerproject.ui.notes.list.NotesViewState

object Store {
    var appState = AppState()

    fun setState(newState: NotesViewState) {
        appState = appState.copy(notesState = newState)
    }

    fun getNoteId(): String {
        return appState.notesState.currentId
    }

    fun getNotesList(): List<NoteBodyUiModel> {
        return appState.notesState.listOfNotes
    }

    fun setState(newState: DetailsViewState) {
        appState = appState.copy(noteDetailsState = newState)
    }
}

data class AppState(
    val notesState: NotesViewState = NotesViewState(0, false, null, {}, {}, "", listOf()),
    val noteDetailsState: DetailsViewState = DetailsViewState(
        {}, {}, {}, "", NoteBodyUiModel(),
        NoteBody(), {}, {}, null
    )
)

// в NotesViewModel
// privat val state: NotesViewState by lazy {
//    Store.appState.notesState
