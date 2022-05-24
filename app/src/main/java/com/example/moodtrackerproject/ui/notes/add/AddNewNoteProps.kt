package com.example.moodtrackerproject.ui.notes.add

import com.example.moodtrackerproject.app.MviAction

data class AddNewNoteProps(
    val action: NewNoteAction? = null,
    val error: NewNoteError? = null,
    val checkNoteData: (String, String) -> Unit,
    val cancelAdding: () -> Unit,
) {
    sealed class NewNoteAction : MviAction {
        object ShowNotesScreen : NewNoteAction()
    }

    sealed class NewNoteError {
        object ShowEmptyTitle : NewNoteError()
    }
}
