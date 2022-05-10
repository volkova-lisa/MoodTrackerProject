package com.example.moodtrackerproject.ui.mood.tests

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.data.DataBaseRepository
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
        viewModel.liveData.value!!.again.invoke()
        binding.optionList.adapter = testAdapter
        binding.progressBar.progress = 0
        binding.progressBar.max = 5
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

            if (state.currQuestionNum < 5) {
                question.setText(state.question.text)
            } else question.setText("The test is finished! Your results are: ")

            testAdapter.setList(state.listOfOptions)
            progressBar.progress = state.currQuestionNum

            if (state.chosenAnswer.text != "") {
                nextButton.isEnabled = true
                nextButton.isClickable = true
                nextButton.setBackgroundResource(R.drawable.round_purple_button)
            }
            if (state.currQuestionNum == 5) nextButton.setText("Finish")
            if (state.currQuestionNum < 5) {
                nextButton.setOnClickListener {
                    val qusNumTop = state.currQuestionNum + 1
                    Log.d("||||||||", state.currQuestionNum.toString())
                    num.setText(qusNumTop.toString())
                    num.append("/5")
                    progressBar.progress = qusNumTop
                    state.moveQuestion.invoke()
                    state.setQuestion.invoke()
                    DataBaseRepository.savePoints(state.chosenAnswer.points)
                    question.setText(state.question.text)
                    Log.d("777777777", DataBaseRepository.points.toString())
                    viewModel.fetchListOfOptions()
                }
            } else {
                (requireActivity() as MainActivity).router.openResults()
            }
            backButt.setOnClickListener {
                state.again.invoke()
                (requireActivity() as MainActivity).router.openMood()
            }
        }
    }
}
