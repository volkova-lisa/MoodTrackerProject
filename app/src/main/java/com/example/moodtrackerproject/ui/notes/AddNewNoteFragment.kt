package com.example.moodtrackerproject.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentAddNewNoteBinding

class AddNewNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNewNoteBinding
    private val viewModel: AddNewNoteViewModel by lazy {
        ViewModelProvider(this).get(AddNewNoteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNewNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    private fun handleError(newNoteError: NewNoteError) {
        when (newNoteError) {
            is NewNoteError.ShowEmptyTitle -> {
                // (requireActivity() as MainActivity).router.openNotesScreen()
            }
        }
    }

    private fun handleAction(newNoteAction: NewNoteAction) {
        when (newNoteAction) {
            is NewNoteAction.ShowNotesScreen -> {
                (requireActivity() as MainActivity).router.openNotesScreen()
            }
        }
    }
    private fun render(state: AddNewNoteViewState) {
        state.action?.let { handleAction(it) }
        state.error?.let { handleError(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            cancelButton.setOnClickListener {
                (requireActivity() as MainActivity).router.openNotesScreen()
            }
            saveButton.setOnClickListener {
                viewModel.checkNoteData(
                    title.text.toString(),
                    noteText.text.toString()
                )
            }
        }
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }
}
