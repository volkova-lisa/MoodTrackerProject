package com.example.moodtrackerproject.ui.notes.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentNotesBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.fetchListOfNotes()
                    viewModel.uiState.collect {
                        notesAdapter.setList(it.listOfNotes)
                        Log.d("999999999999999", it.listOfNotes.toString())
                    }
                }
            }

            addNoteBtn.setOnClickListener {
                (requireActivity() as MainActivity).router.openAddNewNote()
            }
            toolbarStar.setOnClickListener {
                isFavoriteChecked = !isFavoriteChecked
                viewModel.onToolbarStarClicked(isFavoriteChecked)
            }
        }
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
