package com.example.moodtrackerproject.ui.notes.list

data class NotesViewState(
    val notesQuantity: Int = 0,
    val isListEmpty: Boolean = true,
    val action: NotesListAction? = null
)

sealed class NotesListAction {
    class RemoveNote(val position: Int) : NotesListAction()
    class ShowNotesList(val notesList: MutableList<NoteBodyUiModel>) : NotesListAction()
    object StartDetailsScreen : NotesListAction()
}
