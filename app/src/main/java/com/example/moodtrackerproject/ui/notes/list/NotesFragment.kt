package com.example.moodtrackerproject.ui.notes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding

    private val notesAdapter = NotesAdapter()

    val viewModel: NotesViewModel by lazy {
        ViewModelProvider(this).get(NotesViewModel::class.java)
    }

    var isFavoriteChecked = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            notesList.adapter = notesAdapter
            viewModel.notesLiveData.observe(
                viewLifecycleOwner, {
                    notesAdapter.setList(it)
                    // list.map { NotesMapper().map(it) }
                    if (it.isNotEmpty()) {
                        binding.picNoNotes.isInvisible = true
                        binding.hintText.isInvisible = true
                    }
                }
            )

            addNoteBtn.setOnClickListener {
                (requireActivity() as MainActivity).router.openAddNewNote()
            }
            toolbarStar.setOnClickListener {
                isFavoriteChecked = !isFavoriteChecked
                viewModel.onToolbarStarClicked(isFavoriteChecked)
            }
        }
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    private fun render(state: NotesViewState) {
        state.action?.let { handleAction(it) }
    }
    private fun handleAction(noteAction: NotesListAction) {
        when (noteAction) {
            is NotesListAction.RemoveNote -> {
                // (requireActivity() as MainActivity).router.openNotesScreen()
            }
        }
    }
}
