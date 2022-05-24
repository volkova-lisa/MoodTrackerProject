package com.example.moodtrackerproject.ui.notes.list

import com.example.moodtrackerproject.app.MviAction

data class NotesListProps(
    val isFavoriteChecked: Boolean = false,
    val action: NotesListAction? = null,
    val addNewNote: () -> Unit = {},
    val showFavourites: () -> Unit = {},
    val fetchListOfNotes: () -> Unit = {},
    val listOfNotes: List<NoteItemProps> = listOf(),
) {
    sealed class NotesListAction : MviAction {
        object AddNewNote : NotesListAction()
        object StartDetailsScreen : NotesListAction()
    }

    data class NoteItemProps(
        val noteId: String = "", // text.hashCode()//UUID.randomUUID().toString(), //System.currentTimeMillis(),"",
        val date: String = "",
        val editedDate: String = "",
        val title: String = "",
        val text: String = " ",
        val isChecked: Boolean = false,
        val isDeleted: Boolean = false,
        val checkChanged: ((String) -> Unit)? = null,
        val openDetails: ((NoteItemProps) -> Unit)? = null,
        val deleteNote: ((String) -> Unit)? = null
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as NoteItemProps

            if (noteId != other.noteId) return false
            if (date != other.date) return false
            if (editedDate != other.editedDate) return false
            if (title != other.title) return false
            if (text != other.text) return false
            if (isChecked != other.isChecked) return false
            if (isDeleted != other.isDeleted) return false

            return true
        }

        override fun hashCode(): Int {
            var result = noteId.hashCode()
            result = 31 * result + date.hashCode()
            result = 31 * result + editedDate.hashCode()
            result = 31 * result + title.hashCode()
            result = 31 * result + text.hashCode()
            result = 31 * result + isChecked.hashCode()
            result = 31 * result + isDeleted.hashCode()
            return result
        }
    }
}
