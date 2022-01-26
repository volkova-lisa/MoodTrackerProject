package com.example.moodtrackerproject.ui.notes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentNotesBinding
import com.example.moodtrackerproject.ui.notes.list.NotesListAction.AddNewNote
import com.example.moodtrackerproject.ui.notes.list.NotesListAction.StartDetailsScreen
import com.example.moodtrackerproject.utils.click
import com.example.moodtrackerproject.utils.visibleIf

class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private val notesAdapter = NotesAdapter()
    val viewModel: NotesViewModel by lazy {
        ViewModelProvider(this).get(NotesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        viewModel.fetchListOfNotes()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notesList.adapter = notesAdapter
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchListOfNotes()
    }

    private fun render(state: NotesViewState) {

        binding.run {
            addNoteBtn.click(state.addNewNote)
            toolbarStar.click(state.showFavourites)
            picNoNotes.visibleIf(state.listOfNotes.isEmpty())
            hintText.visibleIf(state.listOfNotes.isEmpty())

            notesAdapter.setList(state.listOfNotes)

            when (state.action) {
                AddNewNote -> {
                    (requireActivity() as MainActivity).router.openAddNewNote()
                }
                StartDetailsScreen -> {
                    (requireActivity() as MainActivity).router.openDetails()
                }
                null -> {}
            }
        }
    }
}
