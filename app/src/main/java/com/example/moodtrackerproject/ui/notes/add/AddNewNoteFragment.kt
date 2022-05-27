package com.example.moodtrackerproject.ui.notes.add

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentAddNewNoteBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.notes.add.AddNewNoteProps.NewNoteAction
import com.example.moodtrackerproject.ui.notes.add.AddNewNoteProps.NewNoteError
import com.example.moodtrackerproject.utils.click

class AddNewNoteFragment : BaseFragment<AddNewNoteViewModel, FragmentAddNewNoteBinding, AddNewNoteProps>(
    AddNewNoteViewModel::class.java
) {

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentAddNewNoteBinding = FragmentAddNewNoteBinding.inflate(inflater, container, false)

    override fun render(props: AddNewNoteProps) {
        binding?.run {
            cancelButton.click(props.cancelAdding)
            saveButton.click {
                props.checkNoteData(title.text.toString(), noteText.text.toString())
            }
            if (props.action == NewNoteAction.ShowNotesScreen) {
                (requireActivity() as MainActivity).router.openNotesScreen()
            }
            if (props.error == NewNoteError.ShowEmptyTitle) {
                title.error = getString(R.string.empty_title)
            }
        }
    }
}
