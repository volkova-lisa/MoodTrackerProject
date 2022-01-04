package com.example.moodtrackerproject.ui.notes

import android.os.Bundle
import android.util.Log
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
    private lateinit var observerList: Observer<List<NoteBody>>
    // var allNotes:

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
        viewModel.getAllNotes().observe(
            viewLifecycleOwner,
            Observer {
                val list = it.asReversed()
                Log.d("===================", list.toString())
                notesAdapter.setList(list)
            }
        )
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
