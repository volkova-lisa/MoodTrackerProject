package com.example.moodtrackerproject.ui.notes.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.databinding.FragmentNoteDetailsBinding

class NoteDetailsFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailsBinding
    val viewModel: NoteDetailsViewModel by lazy {
        ViewModelProvider(this).get(NoteDetailsViewModel::class.java)
    }
    private lateinit var thisId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteDetailsBinding.inflate(layoutInflater, container, false)
        thisId = arguments?.getString("ID").toString()
        viewModel.setNoteFromUI(thisId)
        Log.d("-------------------", thisId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    override fun onResume() {
        super.onResume()
    }

    private fun render(state: DetailsViewState) {
        binding.run {
            when (state.action) {
                DetailsAction.SetTitleField -> note.title.text = state.currentNote!!.title
                DetailsAction.SetTextField -> note.text.text = state.currentNote!!.text
                null -> {}
            }
        }
    }
}
