package com.example.moodtrackerproject.ui.notes.list

import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.app.notes.NotesState
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.notes.list.NotesListProps.NotesListAction
import timber.log.Timber

class NotesViewModel : BaseViewModel<NotesListProps>() {

    init {
        setState(Store.appState.notesState)
    }

    private fun map(state: NotesState, action: NotesListAction?): NotesListProps {
        return NotesListProps(
            isFavoriteChecked = state.isFavoriteChecked,
            action = action,
            addNewNote = {
                setState(state, action = NotesListAction.AddNewNote)
            },
            showFavourites = {
                val isFavoriteChecked = !state.isFavoriteChecked
                val noteModels = DataBaseRepository.getNotes()
                    .filter { if (isFavoriteChecked) it.isChecked else true }

                setState(
                    state.copy(
                        listOfNotes = noteModels,
                        isFavoriteChecked = isFavoriteChecked,
                    )
                )
            },
            fetchListOfNotes = {
                val noteModels = DataBaseRepository.getNotes()
                setState(state.copy(listOfNotes = noteModels))
            },
            listOfNotes = state.listOfNotes
                .map { model ->
                    NotesListProps.NoteItemProps(
                        noteId = model.noteId,
                        date = model.date,
                        editedDate = model.editDate,
                        title = model.title,
                        text = model.text,
                        isChecked = model.isChecked,
                        isDeleted = model.isDeleted,
                        checkChanged = {
                            val list = DataBaseRepository.saveFavorite(it)
                            setState(state.copy(listOfNotes = list))
                        },
                        openDetails = {
                            setState(
                                state.copy(currentId = model.noteId),
                                action = NotesListAction.StartDetailsScreen
                            )
                        },
                        deleteNote = { deleted ->
                            Timber.d(deleted)
                            // TODO: maybe combine these two functions because they are similar?
                            DataBaseRepository.setNoteDeleted(model)
                            val list = DataBaseRepository.removeDeletedNotes()
                            setState(state.copy(listOfNotes = list))
                        },
                    )
                }
        )
    }

    // TODO: need to think on some "unification"
    private fun setState(state: NotesState, action: NotesListAction? = null) {
        Store.setState(state)
        liveData.value = map(state, action)
    }
}
