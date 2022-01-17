package com.example.moodtrackerproject.ui.notes.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentAddNewNoteBinding
import com.example.moodtrackerproject.utils.click

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    private fun render(state: AddNewNoteViewState) {
        state.action?.let { handleAction(it) }
        state.error?.let { handleError(it) }
        binding.run {
            cancelButton.click(state.cancelAdding)
            saveButton.click {
                state.checkNoteData(Pair(title.text.toString(), noteText.text.toString()))
            }
            when (state.action) {
                NewNoteAction.ShowNotesScreen -> (requireActivity() as MainActivity).router.openNotesScreen()
                null -> {}
            }
        }
    }

    private fun handleError(newNoteError: NewNoteError) {
        when (newNoteError) {
            is NewNoteError.ShowEmptyTitle -> {
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
}
