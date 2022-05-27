package com.example.moodtrackerproject.ui.mood.test_results

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentTestResultsBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.mood.test_results.TestResultsProps.TestResultsActions
import com.example.moodtrackerproject.utils.click

class TestResultsFragment : BaseFragment<TestResultsViewModel, FragmentTestResultsBinding, TestResultsProps>(
    TestResultsViewModel::class.java
) {

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentTestResultsBinding = FragmentTestResultsBinding.inflate(inflater, container, false)

    override fun render(props: TestResultsProps) {
        binding?.run {
            stressBar.max = props.sumStressPoints
            stressBar.progress = props.stressPoints
            resultNum.text = "${props.resultPer}%"
            backButt.click(props.openMood)
            if (props.action == TestResultsActions.OpenMood) {
                (requireActivity() as MainActivity).router.openMood()
            }
        }
    }
}
