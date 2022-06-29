package com.example.moodtrackerproject.ui.mood.tests

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentStressTestBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.mood.tests.StressTestProps.StressTestActions
import com.example.moodtrackerproject.utils.click

class StressTestFragment : BaseFragment<StressTestViewModel, FragmentStressTestBinding, StressTestProps>(
    StressTestViewModel::class.java
) {

    private lateinit var props: StressTestProps

    private val testAdapter = TestListAdapter()

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentStressTestBinding = FragmentStressTestBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            optionList.adapter = testAdapter
            progressBar.progress = 0
            progressBar.max = 5
        }
    }

    override fun onResume() {
        super.onResume()
        if (::props.isInitialized) {
            props.fetchListOfOptions()
            props.again()
        }
    }

    override fun render(props: StressTestProps) {
        this.props = props
        binding?.run {
            val chosenAnswer = props.listOfOptions.find { it.isChecked }

            Log.d("CUUR QUES NUM", props.currQuestionNum.toString())
            Log.d("STR QUES QUAN", props.stressQuestionsQty.toString())
            Log.d("QUES TEXT", props.questionText.toString())

            question.text = if (props.currQuestionNum < props.stressQuestionsQty) props.questionText
            else getString(R.string.test_finished)
            testAdapter.submitList(props.listOfOptions)
            progressBar.progress = props.currQuestionNum

            nextButton.isEnabled = !chosenAnswer?.text.isNullOrBlank()
            nextButton.isClickable = !chosenAnswer?.text.isNullOrBlank()

            num.text = "${props.currQuestionNum}/${props.stressQuestionsQty}"
            progressBar.progress = props.currQuestionNum

            if (!chosenAnswer?.text.isNullOrBlank()) {
                nextButton.setBackgroundResource(R.drawable.round_purple_button)
            }
            when {
                props.currQuestionNum == props.stressQuestionsQty -> {
                    nextButton.text = getString(R.string.finish)
                }
                props.currQuestionNum < props.stressQuestionsQty -> {
                    nextButton.setOnClickListener {
//                        props.moveQuestion()
//                        Log.d("changed curr num---", props.currQuestionNum.toString())
//                        props.setQuestion()
//                        props.savePoints(chosenAnswer?.points ?: 0)
//                        props.fetchListOfOptions()
                        val questListSize: Int = DataBaseRepository.listOfStressQs.size - 1
                        val qusNumTop = props.currQuestionNum + 1
                        num.setText(qusNumTop.toString())
                        num.append("/$questListSize")
                        progressBar.progress = qusNumTop
                        props.moveQuestion.invoke()
                        props.setQuestion.invoke()
                        DataBaseRepository.saveStressPoints(chosenAnswer?.points ?: 0)
                        question.setText(props.questionText)
                        props.fetchListOfOptions()
                    }
                }
                else -> props.openResults()
            }

            backButt.click {
                props.again()
                props.openMood()
            }

            if (props.action == StressTestActions.OpenMood) {
                (requireActivity() as MainActivity).router.openMood()
            }
            if (props.action == StressTestActions.OpenResults) {
                (requireActivity() as MainActivity).router.openResults()
            }
        }
    }
}
