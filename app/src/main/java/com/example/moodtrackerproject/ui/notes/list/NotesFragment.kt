package com.example.moodtrackerproject.ui.notes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentNotesBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.notes.list.NotesListProps.NotesListAction
import com.example.moodtrackerproject.utils.click
import com.example.moodtrackerproject.utils.visibleIf

class NotesFragment : BaseFragment<NotesViewModel, FragmentNotesBinding, NotesListProps>(
    NotesViewModel::class.java
) {

    private lateinit var props: NotesListProps // FIXME: this is a "crutch" to get notes list

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentNotesBinding = FragmentNotesBinding.inflate(inflater, container, false)

    private val notesAdapter = NotesListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.notesList?.adapter = notesAdapter
    }

    override fun onResume() {
        super.onResume()
        if (::props.isInitialized) {
            props.fetchListOfNotes()
        }
    }

    override fun render(props: NotesListProps) {
        this.props = props
        binding?.run {
            addNoteBtn.click(props.addNewNote)
            toolbarStar.click(props.showFavourites)
            if (!props.isFavoriteChecked) {
                toolbarStar.setImageResource(R.drawable.ic_note_star_unchecked)
                toolbar.title = getString(R.string.my_notes)
            } else {
                toolbarStar.setImageResource(R.drawable.ic_note_star_checked)
                toolbar.title = getString(R.string.fav_notes)
            }
            picNoNotes.visibleIf(props.listOfNotes.isEmpty())
            hintText.visibleIf(props.listOfNotes.isEmpty())

            notesAdapter.submitList(props.listOfNotes)

            props.action?.let { handleAction(it) }
        }
    }

    private fun handleAction(action: NotesListAction) {
        when (action) {
            NotesListAction.AddNewNote -> {
                (requireActivity() as MainActivity).router.openAddNewNote()
            }
            NotesListAction.StartDetailsScreen -> {
                (requireActivity() as MainActivity).router.openDetails()
            }
        }
    }
}
