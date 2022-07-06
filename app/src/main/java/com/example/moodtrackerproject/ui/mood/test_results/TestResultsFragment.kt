package com.example.moodtrackerproject.ui.mood.test_results

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.data.DataBaseRepository
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
            if (props.testType == 0) {
                val pers = (DataBaseRepository.stressPoints * 100) / 25
                stressBar.max = props.sumTestPoints
                stressBar.progress = DataBaseRepository.stressPoints
                resultNum.text = "$pers%"
                backButt.click(props.openMood)
            } else {
                val pers = (DataBaseRepository.anxietyPoints * 100) / 25
                stressBar.max = props.sumTestPoints
                stressBar.progress = DataBaseRepository.anxietyPoints
                resultNum.text = "$pers%"
                backButt.click(props.openMood)
            }
            if (props.action == TestResultsActions.OpenMood) {
                (requireActivity() as MainActivity).router.openMood()
            }
        }
    }
}
