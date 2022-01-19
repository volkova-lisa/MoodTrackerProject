package com.example.moodtrackerproject.ui.notes.list

data class NotesViewState(
    val notesQuantity: Int = 0,
    val isFavoriteChecked: Boolean = false,
    val action: NotesListAction? = null,
    val addNewNote: () -> Unit,
    val showFavourites: () -> Unit,
    val currentId: String = "",
    val listOfNotes: List<NoteBodyUiModel> = listOf()
)

sealed class NotesListAction {
    object AddNewNote : NotesListAction()
    object StartDetailsScreen : NotesListAction()
}
