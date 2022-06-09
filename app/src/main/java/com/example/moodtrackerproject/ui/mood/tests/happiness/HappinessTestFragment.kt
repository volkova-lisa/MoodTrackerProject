package com.example.moodtrackerproject.ui.mood.tests.anger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentHappinessTestBinding
import com.example.moodtrackerproject.ui.mood.tests.TestAdapter
import com.example.moodtrackerproject.utils.click

class HappinessTestFragment : Fragment() {

    private lateinit var binding: FragmentHappinessTestBinding
    private val testAdapter = TestAdapter()
    val viewModel: HappinesViewModel by lazy {
        ViewModelProvider(this).get(HappinesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHappinessTestBinding.inflate(layoutInflater, container, false)
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
        viewModel.liveData.value?.let { binding.question.setText(it.question.text) }
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchListOfOptions()
    }

    private fun render(state: HappinesTestState) {
        binding.run {

            // TODO: less BL
            val questListSize: Int = DataBaseRepository.listOfStressQs.size - 1
            if (state.currQuestionNum < questListSize) {
                question.setText(state.question.text)
            } else question.setText(getString(R.string.test_finished))

            testAdapter.setList(state.listOfOptions)
            progressBar.progress = state.currQuestionNum

            if (state.chosenAnswer.text.isNotBlank()) {
                nextButton.isEnabled = true
                nextButton.isClickable = true
                nextButton.setBackgroundResource(R.drawable.round_purple_button)
            }
            if (state.currQuestionNum == questListSize) nextButton.setText(getString(R.string.finish))
            if (state.currQuestionNum < questListSize) {
                nextButton.setOnClickListener {
                    val qusNumTop = state.currQuestionNum + 1
                    num.setText(qusNumTop.toString())
                    num.append("/$questListSize")
                    progressBar.progress = qusNumTop
                    state.moveQuestion.invoke()
                    state.setQuestion.invoke()
                    DataBaseRepository.saveHappinessPoints(state.chosenAnswer.points)
                    question.setText(state.question.text)
                    viewModel.fetchListOfOptions()
                }
            } else {
                (requireActivity() as MainActivity).router.openHappinessResults()
            }
            backButt.click {
                state.again.invoke()
                (requireActivity() as MainActivity).router.openMood()
            }
        }
    }
}
