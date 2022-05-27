package com.example.moodtrackerproject.ui.notes.add

import com.example.moodtrackerproject.app.AppState
import com.example.moodtrackerproject.app.MviAction
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.domain.NoteModel
import com.example.moodtrackerproject.ui.BaseViewModel
import com.example.moodtrackerproject.ui.notes.add.AddNewNoteProps.NewNoteAction
import com.example.moodtrackerproject.ui.notes.add.AddNewNoteProps.NewNoteError
import com.example.moodtrackerproject.utils.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddNewNoteViewModel : BaseViewModel<AddNewNoteProps>() {

    private val props = AddNewNoteProps(
        cancelAdding = ::cancelAdding,
        checkNoteData = ::checkNoteData,
        action = null,
        error = null,
    )

    init {
        liveData.value = props
    }

    private fun cancelAdding() {
        liveData.value = props.copy(action = NewNoteAction.ShowNotesScreen)
    }

    private fun checkNoteData(title: String, text: String) {
        if (title.isEmpty()) {
            liveData.value = props.copy(error = NewNoteError.ShowEmptyTitle)
        } else {
            val noteModel = NoteModel(
                date = DateUtils.getDateOfNote(),
                title = title,
                text = text,
            )
            insertNewNote(noteModel)
        }
    }

    private fun insertNewNote(note: NoteModel) {
        launch {
            withContext(Dispatchers.IO) {
                DataBaseRepository.insertNote(note)
            }
            liveData.value = props.copy(action = NewNoteAction.ShowNotesScreen)
        }
    }

    override fun map(appState: AppState, action: MviAction?): AddNewNoteProps {
        TODO("Not yet implemented")
    }
}
