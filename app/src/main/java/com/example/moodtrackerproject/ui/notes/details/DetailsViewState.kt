package com.example.moodtrackerproject.ui.notes.details

import com.example.moodtrackerproject.domain.NoteBody

data class DetailsViewState(
    val editClicked: () -> Unit,
    val backClicked: () -> Unit,
    val saveEdited: () -> Unit,

    val currentId: String = " ",
    val currentNote: NoteBody? = NoteBody(),
    val currentTitle: String = " ",
    val currentText: String = " ",

    val setId: () -> Unit,
    val setNote: () -> Unit,

    val action: DetailsAction? = null,
)

sealed class DetailsAction {
    object CancelEditing : DetailsAction()
    object SwitchToEditable : DetailsAction()
    object ShowAllNotes : DetailsAction()
}
