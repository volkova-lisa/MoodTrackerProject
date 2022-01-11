package com.example.moodtrackerproject.ui.notes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentNotesBinding
import com.example.moodtrackerproject.domain.map.NotesMapper

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding

    private val notesAdapter = NotesAdapter()

    private val viewModel: NotesViewModel by lazy {
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
            viewModel.uiModels.observe(
                viewLifecycleOwner, {
                    val list = it!!.asReversed()
                    // ToDo (NotesMapper)
                    notesAdapter.setList(list.map { NotesMapper().map(it) })
                }
            )

            addNoteBtn.setOnClickListener {
                (requireActivity() as MainActivity).router.openAddNewNote()
            }
            toolbarStar.setOnClickListener {
                // set here some value to edit list in repo
                viewModel.onToolbarStarClicked(true)
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
