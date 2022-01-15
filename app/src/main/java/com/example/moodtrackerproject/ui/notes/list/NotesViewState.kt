package com.example.moodtrackerproject.ui.notes.list

data class NotesViewState(
    val notesQuantity: Int = 0,
    val isListEmpty: Boolean = true,
    val action: NotesListAction? = null,
    val listOfNotes: List<NoteBodyUiModel> = listOf()
)

sealed class NotesListAction {
    class RemoveNote(val position: Int) : NotesListAction()
    object StartDetailsScreen : NotesListAction()
}
