package com.example.moodtrackerproject.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.data.NoteBody
import com.example.moodtrackerproject.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var recyclerView: RecyclerView

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
            notesAdapter = NotesAdapter()
            recyclerView = binding.notesList
            recyclerView.adapter = notesAdapter

            viewModel.getAllNotes().observe(
                viewLifecycleOwner,
                Observer {
                    val list = it!!.asReversed()
                    notesAdapter.setList(list)
                }
            )
            toolbarStar.setOnClickListener {
                viewModel.isChecked = !viewModel.isChecked
                if (viewModel.isChecked) {
                    toolbarStar.setImageResource(R.drawable.ic_note_star_checked)
                    viewModel.getFavNotes().observe(
                        viewLifecycleOwner,
                        Observer {
                            val list = it.asReversed()
                            notesAdapter.setList(list)
                        }
                    )
                } else {
                    toolbarStar.setImageResource(R.drawable.ic_note_star_unchecked)
                    viewModel.getAllNotes().observe(
                        viewLifecycleOwner,
                        Observer {
                            val list = it!!.asReversed()
                            notesAdapter.setList(list)
                        }
                    )
                }
            }

            addNoteBtn.setOnClickListener {
                (requireActivity() as MainActivity).router.openAddNewNote()
            }
        }
    }

    companion object {
        fun click(note: NoteBody) {
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            // go inside note
            // (requireActivity() as MainActivity).router.openAddNewNote()
        }
    }

    fun showFavourites(): Boolean {
        return true
    }
}
