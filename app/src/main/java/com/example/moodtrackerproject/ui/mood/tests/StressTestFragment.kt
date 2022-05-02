package com.example.moodtrackerproject.ui.mood.tests

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.optionList.adapter = testAdapter
        binding.question.text = DataBaseRepository.listOfStressQs[viewModel.liveData.value?.currQuestionNum!!].text
        viewModel.fetchListOfOptions()
        binding.nextButton.isEnabled = false
        binding.nextButton.isClickable = false
        Log.d("onViewCr---------", viewModel.liveData.value?.currQuestionNum.toString())
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
            binding.question.text = DataBaseRepository.listOfStressQs[it.currQuestionNum].text
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchListOfOptions()
        Log.d("onResume-----", viewModel.liveData.value?.currQuestionNum.toString())
    }

    private fun render(state: StressTestState) {
        binding.question.text = DataBaseRepository.listOfStressQs[state.currQuestionNum].text
        binding.run {
            testAdapter.setList(state.listOfOptions)
            Log.d("notClicked----", state.question.text.toString())
            if (state.chosenAnswer.text != "") {
                nextButton.isEnabled = true
                nextButton.isClickable = true
                nextButton.setBackgroundResource(R.drawable.round_purple_button)
            }
            nextButton.setOnClickListener {
                Log.d("notInvoked-----", state.currQuestionNum.toString())
                state.moveQuestion.invoke(state.currQuestionNum)
                Log.d("invoked----", question.text.toString())
                viewModel.fetchListOfOptions()
            }
            Log.d("afterClicked----", state.currQuestionNum.toString())
        }
    }
}
