package com.example.moodtrackerproject.ui.notes.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentNoteDetailsBinding
import com.example.moodtrackerproject.utils.click

class NoteDetailsFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailsBinding

    private val viewModel: NoteDetailsViewModel by lazy {
        ViewModelProvider(this).get(NoteDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.value?.setNote?.invoke()
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    private fun render(state: DetailsViewState) {
        binding.run {
            // TODO: make it via state.isEditNoteVisible
            if (state.currentNote != null) {
                note.title.text = state.currentNote.title
                note.text.text = state.currentNote.text
                note.date.text = state.currentNote.date
                note.editedDate.text = "edited" + state.currentNote.editedDate
            }
            note.backButton.click(state.backClicked)
            noteEdit.cancelButton.click {
                noteEdit.root.isVisible = false
                note.root.isVisible = true
            }

            editButton.click {
                noteEdit.root.isVisible = true
                note.root.isVisible = false
                editButton.isVisible = false
                state.currentNote?.let {
                    noteEdit.title.setText(state.currentNote.title)
                    noteEdit.text.setText(state.currentNote.text)
                }
            }

            noteEdit.saveEditedButton.click {
                val newTitle = noteEdit.title.text.toString()
                val newText = noteEdit.text.text.toString()

                viewModel.saveEdited(newTitle, newText)
                viewModel.liveData.value?.cancelClicked?.invoke()
            }

            when (state.action) {
                DetailsAction.CancelEditing -> (requireActivity() as MainActivity).router.openDetails()
                DetailsAction.ShowAllNotes -> (requireActivity() as MainActivity).router.openNotesScreen()
                null -> {}
            }
        }
    }
}
