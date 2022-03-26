package com.example.moodtrackerproject.ui.mood.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentMoodBinding
import com.example.moodtrackerproject.ui.mood.list.MoodScreenActions.StartAddMoodScreen

class MoodFragment : Fragment() {

    private lateinit var binding: FragmentMoodBinding
    private val moodsAdapter = MoodAdapter()
    val viewModel: MoodViewModel by lazy {
        ViewModelProvider(this).get(MoodViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoodBinding.inflate(layoutInflater, container, false)
        viewModel.fetchListOfMoods()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.emojiList.adapter = moodsAdapter
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    private fun render(state: MoodViewState) {
        binding.run {
            moodInclude.addMoodBtn.setOnClickListener {
                state.addNewMood.invoke()
            }
            moodsAdapter.setList(state.listOfMoods)
            Log.d(";;;;;;;;;", state.listOfMoods.toString())
        }
        when (state.action) {
            StartAddMoodScreen -> {
                (requireActivity() as MainActivity).router.openAddMood()
            }
            null -> {}
        }
    }
}
