package com.example.moodtrackerproject.ui.notes.add

import com.example.moodtrackerproject.domain.NoteBody

data class AddNewNoteViewState(
    val action: NewNoteAction? = null,
    val error: NewNoteError? = null,
    val checkNoteData: (Pair<String, String>) -> Unit,
    val cancelAdding: () -> Unit,
    val saveNewNote: (NoteBody) -> Unit
)

sealed class NewNoteAction {
    object ShowNotesScreen : NewNoteAction()
}

sealed class NewNoteError {
    object ShowEmptyTitle : NewNoteError()
}
