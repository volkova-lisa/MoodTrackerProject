package com.example.moodtrackerproject.ui.notes

import android.annotation.SuppressLint
import android.graphics.drawable.BitmapDrawable
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
import com.example.moodtrackerproject.R
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
        if (onStarClicked()) {
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
                    val list = it.asReversed()
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

    @SuppressLint("UseCompatLoadingForDrawables")
    fun onStarClicked(): Boolean {
        binding.run {
            var checked = false
            toolbarStar.setOnClickListener {
                Log.d("==================", toolbarStar.drawable.toString())
                Log.d(
                    "==================", resources.getDrawable(R.drawable.ic_note_star_unchecked).toString()
                )
                val bitmapCheckedStar = (resources.getDrawable(R.drawable.ic_note_star_unchecked) as BitmapDrawable).bitmap
                val bitmapUncheckedStar = (resources.getDrawable(R.drawable.ic_note_star_checked) as BitmapDrawable).bitmap

                checked =
                    if ((toolbarStar.drawable as BitmapDrawable).bitmap == bitmapUncheckedStar) {
                        binding.toolbarStar.setImageResource(R.drawable.ic_note_star_checked)
                        Log.d("----------------", "------------")
                        true
                    } else {
                        binding.toolbarStar.setImageResource(R.drawable.ic_note_star_unchecked)
                        Log.d("+++++++++++++++", "+++++++++++++")
                        false
                    }
            }
            return checked
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
