package com.example.moodtrackerproject.ui.mood.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentAddMoodBinding
import com.example.moodtrackerproject.utils.click

class AddMoodFragment : Fragment() {

    private lateinit var binding: FragmentAddMoodBinding
    private val addMoodAdapter = AddMoodAdapter()
    private val viewModel: AddMoodViewModel by lazy {
        ViewModelProvider(this).get(AddMoodViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMoodBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.emojiInclude.emojiGrid.layoutManager = GridLayoutManager(context, 4)
        binding.emojiInclude.emojiGrid.adapter = addMoodAdapter
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchListOfMoods()
    }

    private fun render(state: AddMoodViewState) {
        addMoodAdapter.setList(state.listWithChosenMood)

        binding.run {
            val text = state.listWithChosenMood.find {
                it.isChecked
            }
            emojiName.text = text?.title ?: "Mood title"
            //TODO: fix image with firebase
            saveButton.click {
                state.saveMood(Pair(state.listWithChosenMood[1].image, emojiName.text.toString()))
            }
            when (state.action) {
                NewMoodAction.ShowMoodsScreen -> (requireActivity() as MainActivity).router.openMood()
                null -> {}
            }
        }
    }
}
