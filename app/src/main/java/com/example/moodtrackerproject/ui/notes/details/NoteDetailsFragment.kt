package com.example.moodtrackerproject.ui.notes.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentNoteDetailsBinding
import com.example.moodtrackerproject.utils.click

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
            note.backButton.click(state.backClicked)

            when (state.action) {
                DetailsAction.CancelEditing -> (requireActivity() as MainActivity).router.openNotesScreen()
                null -> {}
            }
        }
    }
}
