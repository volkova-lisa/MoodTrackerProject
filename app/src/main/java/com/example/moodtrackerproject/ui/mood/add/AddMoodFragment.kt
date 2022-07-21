package com.example.moodtrackerproject.ui.mood.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentAddMoodBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.mood.add.AddMoodProps.NewMoodAction
import com.example.moodtrackerproject.utils.click

class AddMoodFragment : BaseFragment<AddMoodViewModel, FragmentAddMoodBinding, AddMoodProps>(
    AddMoodViewModel::class.java
) {

    private lateinit var props: AddMoodProps

    private val addMoodAdapter = AddMoodAdapter()

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentAddMoodBinding = FragmentAddMoodBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            emojiInclude.emojiGrid.layoutManager = GridLayoutManager(context, 4)
            emojiInclude.emojiGrid.adapter = addMoodAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        if (::props.isInitialized) {
            props.fetchListOfMoods()
        }
    }

    override fun render(props: AddMoodProps) {
        this.props = props
        binding?.run {
            addMoodAdapter.submitList(props.moodItems)
            val mood = props.moodItems.find { it.isChecked }
            emojiName.text = mood?.title ?: getString(R.string.mood_t)
            saveButton.click({
                mood?.let { props.saveMood(it.image, emojiName.text.toString()) }
            })
            if (props.action == NewMoodAction.ShowMoodsScreen) {
                (requireActivity() as MainActivity).router.openMood()
            }
        }
    }
}
