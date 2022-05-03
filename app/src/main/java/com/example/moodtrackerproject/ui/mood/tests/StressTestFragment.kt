package com.example.moodtrackerproject.ui.mood.tests

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
        binding.question.setText(viewModel.liveData!!.value!!.question.text)
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
            Log.d("outside---------", state.currQuestionNum.toString())

            question.setText(state.question.text)
            testAdapter.setList(state.listOfOptions)
            if (state.chosenAnswer.text != "") {
                nextButton.isEnabled = true
                nextButton.isClickable = true
                nextButton.setBackgroundResource(R.drawable.round_purple_button)
            }
            if (state.currQuestionNum < 4) {
                nextButton.setOnClickListener {
                    state.moveQuestion.invoke()
                    state.setQuestion.invoke()
                    question.setText(state.question.text.toString())
                    Log.d("inside---------", state.currQuestionNum.toString())
                    viewModel.fetchListOfOptions()
                }
            } else {
                question.setText("finish")
                nextButton.isVisible = false
            }
        }
    }
}
