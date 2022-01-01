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
    private val notesAdapter: NotesAdapter = NotesAdapter()
    private lateinit var recyclerView: RecyclerView
    private var observerList: Observer<List<NoteBody>> = Observer {
        val list = it.asReversed()
        notesAdapter.setList(list)
    }
    // var allNotes:

    private val viewModel: AddNewNoteViewModel by lazy {
        ViewModelProvider(this).get(AddNewNoteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        recyclerView = binding.notesList
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = notesAdapter
        // viewModel.allNotes.observe(this, observerList)
        viewModel.allNotes.observe(viewLifecycleOwner, observerList)
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
        }
    }
}
