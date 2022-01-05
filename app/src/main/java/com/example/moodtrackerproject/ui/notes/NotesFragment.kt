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
        notesAdapter = NotesAdapter()
        recyclerView = binding.notesList
        recyclerView.adapter = notesAdapter
        if (viewModel.isChecked) {
            // in case menu star clicked
            viewModel.getFavNotes().observe(
                viewLifecycleOwner,
                Observer {
                    val list = it.asReversed()
                    notesAdapter.setList(list)
                }
            )
        } else {
            // in case menu star not clicked
            viewModel.getAllNotes().observe(
                viewLifecycleOwner,
                Observer {
                    val list = it!!.asReversed()
                    notesAdapter.setList(list)
                }
            )
        }
        binding.run {
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
}
