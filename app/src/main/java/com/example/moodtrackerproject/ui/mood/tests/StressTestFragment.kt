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
        viewModel.fetchListOfOptions()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.optionList.adapter = testAdapter
        binding.nextButton.isEnabled = false
        binding.nextButton.isClickable = false
        Log.d("onViewCr---------", viewModel.liveData.value?.currQuestionNum.toString())
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
            // question.setText(state.question.text)
            testAdapter.setList(state.listOfOptions)
            if (state.chosenAnswer.text != "") {
                nextButton.isEnabled = true
                nextButton.isClickable = true
                nextButton.setBackgroundResource(R.drawable.round_purple_button)
            }
            nextButton.setOnClickListener {
                val num = state.currQuestionNum + 1
                state.copy(currQuestionNum = num)
                state.setQuestion(state.currQuestionNum)
                // state.setQuestion(state.currQuestionNum)
                Log.d("========", state.currQuestionNum.toString())
                Log.d("55555555", num.toString())
                question.setText(state.question.text)
                viewModel.fetchListOfOptions()
            }
        }
    }
}
