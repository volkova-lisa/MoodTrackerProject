package com.example.moodtrackerproject.ui.notes

data class AddNewNoteViewState(
    val action: NewNoteAction? = null,
    val error: NewNoteError? = null,
)

sealed class NewNoteAction {
    object ShowNotesScreen : NewNoteAction()
}

sealed class NewNoteError {
    object ShowEmptyTitle : NewNoteError()
}
