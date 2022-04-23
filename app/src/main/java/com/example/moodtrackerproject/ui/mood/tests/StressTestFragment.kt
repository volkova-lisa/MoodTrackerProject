package com.example.moodtrackerproject.ui.mood.tests

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentStressTestBinding

class StressTestFragment : Fragment() {

    private lateinit var binding: FragmentStressTestBinding
    private val testAdapter = TestAdapter()
    val viewModel: StressTestViewModel by lazy {
        ViewModelProvider(this).get(StressTestViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStressTestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.value?.setQuestion?.invoke(0)
        binding.optionList.adapter = testAdapter
        viewModel.fetchListOfOptions()
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchListOfOptions()
    }

    private fun render(state: StressTestState) {
        binding.run {
            question.text = state.question.text
            testAdapter.setList(state.listOfOptions)
            nextButton.isEnabled = false

            if (state.chosenAnswer.text != "") {
                nextButton.isEnabled = true
                nextButton.setBackgroundResource(R.drawable.round_purple_button)
                nextButton.setTextColor(R.color.white)
            }
            nextButton.setOnClickListener {
                nextButton.setBackgroundResource(R.drawable.stress_bg)
            }
            Log.d("----opti------", state.chosenAnswer.toString())
        }
    }
}
