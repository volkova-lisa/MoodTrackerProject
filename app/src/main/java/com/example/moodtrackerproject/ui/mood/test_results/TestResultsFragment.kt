package com.example.moodtrackerproject.ui.mood.test_results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentTestResultsBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.mood.test_results.TestResultsProps.TestResultsActions
import com.example.moodtrackerproject.utils.click

class TestResultsFragment : BaseFragment<TestResultsViewModel, FragmentTestResultsBinding, TestResultsProps>(
    TestResultsViewModel::class.java
) {

    private lateinit var props: TestResultsProps

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentTestResultsBinding = FragmentTestResultsBinding.inflate(inflater, container, false)

    override fun onResume() {
        super.onResume()
        if (::props.isInitialized) {
            props.fetchTestResults()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (::props.isInitialized) {
            props.fetchTestResults()
        }
    }

    override fun render(props: TestResultsProps) {
        binding?.run {

            if (props.testResults != null) {

                if (props.testType == 0) {
                    val pers = (props.testResults.stressResult * 100) / 25
                    stressBar.max = props.sumTestPoints
                    stressBar.progress = DataBaseRepository.stressPoints
                    resultNum.text = "$pers%"
                    backButt.click(props.openMood)
                    resultText.mainTitle.text = getText(R.string.stress_high_title)
                    resultText.subtitle.text = getText(R.string.stress_high)
                } else {
                    val pers = (props.testResults.anxResult * 100) / 25
                    stressBar.max = props.sumTestPoints
                    stressBar.progress = DataBaseRepository.anxietyPoints
                    resultNum.text = "$pers%"
                    backButt.click(props.openMood)
                    resultMini.text = "of anxiety"
                }
            }
            if (props.action == TestResultsActions.OpenMood) {
                (requireActivity() as MainActivity).router.openMood()
            }
        }
    }
}
