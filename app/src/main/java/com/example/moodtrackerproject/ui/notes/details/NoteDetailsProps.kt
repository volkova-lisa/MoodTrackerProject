package com.example.moodtrackerproject.ui.notes.details

import com.example.moodtrackerproject.app.MviAction

data class NoteDetailsProps(
    val noteId: String = "",
    val date: String = "",
    val editedDate: String = "",
    val title: String = "",
    val text: String = " ",
    val editClicked: () -> Unit,
    val isEditNoteVisible: Boolean = false,
    val changeEditVisibility: () -> Unit,
    val backClicked: () -> Unit,
    val cancelClicked: () -> Unit,
    val saveEdited: (String, String) -> Unit,
    val setNote: () -> Unit,
    val action: DetailsAction? = null,
) {
    sealed class DetailsAction : MviAction {
        object CancelEditing : DetailsAction()
        object ShowAllNotes : DetailsAction()
    }
}
