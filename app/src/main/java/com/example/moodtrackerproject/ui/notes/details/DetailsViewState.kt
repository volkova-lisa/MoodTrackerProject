package com.example.moodtrackerproject.ui.notes.details

data class DetailsViewState (
    val editClicked: () -> Unit,
    val backClicked: () -> Unit,
    val saveEdited: () -> Unit,
    val action: DetailsAction? = null,
    )

sealed class DetailsAction{
    object SwitchToEditable : DetailsAction()
    object ShowAllNotes: DetailsAction()
}