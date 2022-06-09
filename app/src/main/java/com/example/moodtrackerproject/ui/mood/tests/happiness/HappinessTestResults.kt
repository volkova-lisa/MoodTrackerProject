package com.example.moodtrackerproject.ui.mood.tests.anger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentHappinessTestResultsBinding

class HappinessTestResults : Fragment() {

    private lateinit var binding: FragmentHappinessTestResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHappinessTestResultsBinding.inflate(layoutInflater, container, false)
        val maxPoint = DataBaseRepository.getOptions().size
        val sumAnxietyPoints = maxPoint * (DataBaseRepository.listOfStressQs.size - 1)
        binding.stressBar.max = sumAnxietyPoints
        binding.stressBar.progress = DataBaseRepository.happinessResults
        val resultPer: Int = (DataBaseRepository.happinessResults * 100) / 25
        binding.resultNum.setText("$resultPer")
        binding.resultNum.append("%")
        DataBaseRepository.saveHappinessResults(resultPer)
        binding.backButt.setOnClickListener {
            (requireActivity() as MainActivity).router.openMood()
        }
        return binding.root
    }
}
