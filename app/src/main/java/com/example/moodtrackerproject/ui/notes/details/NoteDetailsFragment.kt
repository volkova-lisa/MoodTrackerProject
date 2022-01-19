package com.example.moodtrackerproject.ui.notes.details

import android.os.Bundle
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    private fun render(state: DetailsViewState) {
    }
}
