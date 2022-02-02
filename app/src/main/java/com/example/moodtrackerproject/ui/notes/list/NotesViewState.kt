package com.example.moodtrackerproject.ui.notes.list

data class NotesViewState(
    val notesQuantity: Int = 0,
    val isFavoriteChecked: Boolean = false,
    val action: NotesListAction? = null,
    val addNewNote: () -> Unit = {},
    val showFavourites: () -> Unit = {},
    val currentId: String = "",
    // TODO: List<NoteBodyUiModel> should become List<NoteBody>
    val listOfNotes: List<NoteBodyUiModel> = listOf(),
    val textEdited: Boolean = false
)

sealed class NotesListAction {
    object AddNewNote : NotesListAction()
    object StartDetailsScreen : NotesListAction()
}
