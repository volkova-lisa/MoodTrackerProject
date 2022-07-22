package com.example.moodtrackerproject.ui.notes.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentNoteDetailsBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.notes.details.NoteDetailsProps.DetailsAction
import com.example.moodtrackerproject.utils.click

class NoteDetailsFragment : BaseFragment<NoteDetailsViewModel, FragmentNoteDetailsBinding, NoteDetailsProps>(
    NoteDetailsViewModel::class.java
) {

    private lateinit var props: NoteDetailsProps

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentNoteDetailsBinding = FragmentNoteDetailsBinding.inflate(inflater, container, false)

    override fun onResume() {
        super.onResume()
        if (::props.isInitialized) {
            props.setNote()
        }
    }

    override fun render(props: NoteDetailsProps) {
        this.props = props
        binding?.run {
            note.title.text = props.title
            note.text.text = props.text
            note.date.text = props.date
            if (props.editedDate.isNotEmpty()) {
                note.editedDate.text = "edited ${props.editedDate}"
            }

            noteEdit.root.isVisible = props.isEditNoteVisible
            note.root.isVisible = !props.isEditNoteVisible
            editNoteButton.isVisible = !props.isEditNoteVisible
            noteEdit.title.setText(props.title)
            noteEdit.text.setText(props.text)

            note.backButton.click(props.backClicked)
            editNoteButton.click(props.changeEditVisibility)

            noteEdit.saveEditedButton.click({
                props.saveEdited(noteEdit.title.text.toString(), noteEdit.text.text.toString())
            })

            props.action?.let { handleAction(it) }
        }
    }

    private fun handleAction(action: DetailsAction) {
        when (action) {
            DetailsAction.CancelEditing -> (requireActivity() as MainActivity).router.openDetails()
            DetailsAction.ShowAllNotes -> (requireActivity() as MainActivity).router.openNotesScreen()
        }
    }
}
