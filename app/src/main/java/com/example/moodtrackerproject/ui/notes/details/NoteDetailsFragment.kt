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
    val viewModel: NoteDetailsViewModel by lazy {
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
            // here we set main text for details ui
            note.title.text = state.currentNote!!.noteId // change to title
            note.text.text = state.currentNote!!.text
            note.date.text = state.currentNote!!.date

            // here back buttons on details screen behavior
            note.backButton.click(state.backClicked)
            noteEdit.cancelButton.click {
                noteEdit.root.isVisible = false
                note.root.isVisible = true
            }

            editButton.click {
                noteEdit.root.isVisible = true
                note.root.isVisible = false
                editButton.isVisible = false

                noteEdit.title.setText(state.currentNote!!.title)
                noteEdit.text.setText(state.currentNote!!.text)
            }

            noteEdit.saveEditedButton.click {
                val newTitle = noteEdit.title.text.toString()
                val newText = noteEdit.text.text.toString()

                viewModel.saveEdited(newTitle, newText)
                viewModel.liveData.value?.cancelClicked?.invoke()
            }
            // note.title.text = newTitle
            // note.title.text = newText

            when (state.action) {
                DetailsAction.CancelEditing -> (requireActivity() as MainActivity).router.openDetails()
                DetailsAction.ShowAllNotes -> (requireActivity() as MainActivity).router.openNotesScreen()

                null -> {}
            }
        }
    }
}
