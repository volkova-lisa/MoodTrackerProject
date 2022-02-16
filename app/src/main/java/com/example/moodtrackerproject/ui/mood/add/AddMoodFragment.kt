package com.example.moodtrackerproject.ui.mood.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentAddMoodBinding

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

    private fun render(state: AddMoodViewState) {
        // remove from here later
        val emojiList: List<Int> = listOf(
            R.drawable.emoji_anger, R.drawable.emoji_awesome,
            R.drawable.emoji_calm, R.drawable.emoji_concentrated,
            R.drawable.emoji_cool, R.drawable.emoji_crying,
            R.drawable.emoji_crying_inside, R.drawable.emoji_excited,
            R.drawable.emoji_furious, R.drawable.emoji_great,
            R.drawable.emoji_puking, R.drawable.emoji_sad,
            R.drawable.emoji_shocked, R.drawable.emoji_sick,
            R.drawable.emoji_sleepy, R.drawable.emoji_traumatised
        )
        addMoodAdapter.setList(emojiList)
        Log.d("========", "---")
    }
}
