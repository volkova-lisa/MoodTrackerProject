package com.example.moodtrackerproject.ui.notes.details

import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.ui.notes.list.NoteBodyUiModel

data class DetailsViewState(
    val editClicked: () -> Unit,
    val backClicked: () -> Unit,
    val cancelClicked: () -> Unit,

    val currentId: String = " ",
    val currentNote: NoteBodyUiModel? = NoteBodyUiModel(),
    val noteCurrent: NoteBody? = NoteBody(),

    val setId: () -> Unit,
    val setNote: () -> Unit,

    val action: DetailsAction? = null,
)

sealed class DetailsAction {
    object CancelEditing : DetailsAction()
    object SwitchToEditable : DetailsAction()
    object ShowAllNotes : DetailsAction()
}
