package com.example.moodtrackerproject.ui.notes.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
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
                        if (it.listOfNotes.isNotEmpty()) {
                            picNoNotes.isInvisible = true
                            hintText.isInvisible = true
                        }
                        notesAdapter.setList(it.listOfNotes)
                    }
                }
            }
            addNoteBtn.setOnClickListener {
                (requireActivity() as MainActivity).router.openAddNewNote()
            }
            toolbarStar.setOnClickListener {
                //here it works only once
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.fetchListOfNotes()
                        viewModel.uiState.collect() {
                            viewModel.onToolbarStarClicked(it.isFavoriteChecked)
                            Log.d("888888888888888888", it.isFavoriteChecked.toString())
                        }
                    }
                }
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
            is NotesListAction.ChangeFavoriteStatus -> {
                viewModel.changeStarState()
            }
        }
    }
}
