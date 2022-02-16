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
        val emojiList: List<EmojiBody> = listOf(
            EmojiBody(R.drawable.emoji_anger), EmojiBody(R.drawable.emoji_awesome), EmojiBody(R.drawable.emoji_calm), EmojiBody(R.drawable.emoji_concentrated),
            EmojiBody(R.drawable.emoji_cool), EmojiBody(R.drawable.emoji_crying), EmojiBody(R.drawable.emoji_crying_inside), EmojiBody(R.drawable.emoji_excited),
            EmojiBody(R.drawable.emoji_furious), EmojiBody(R.drawable.emoji_great), EmojiBody(R.drawable.emoji_puking), EmojiBody(R.drawable.emoji_sad),
            EmojiBody(R.drawable.emoji_shocked), EmojiBody(R.drawable.emoji_sick), EmojiBody(R.drawable.emoji_sleepy), EmojiBody(R.drawable.emoji_traumatised),
        )
        addMoodAdapter.setList(emojiList)
        Log.d("========", "---")
    }
}
