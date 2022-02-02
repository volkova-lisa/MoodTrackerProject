package com.example.moodtrackerproject.ui.notes.details

import com.example.moodtrackerproject.ui.notes.list.NoteBodyUiModel

data class DetailsViewState(
    val editClicked: () -> Unit = {},
    val isEditNoteVisible: Boolean = false,
    val changeEditVisibility: () -> Unit = {},
    val backClicked: () -> Unit = {},
    val cancelClicked: () -> Unit = {},
    //TODO: NoteBodyUiModel replace with NoteBody
    val currentNote: NoteBodyUiModel? = NoteBodyUiModel(),
    val setNote: () -> Unit = {},
    val action: DetailsAction? = null,
)

sealed class DetailsAction {
    object CancelEditing : DetailsAction()
    object ShowAllNotes : DetailsAction()
}
