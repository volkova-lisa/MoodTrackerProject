package com.example.moodtrackerproject.ui.mood.tests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentTestResultsBinding

class TestResults : Fragment() {

    private lateinit var binding: FragmentTestResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestResultsBinding.inflate(layoutInflater, container, false)
        val maxPoint = DataBaseRepository.getOptions().size
        val sumStressPoints = maxPoint * (DataBaseRepository.listOfStressQs.size - 1)
        binding.stressBar.max = sumStressPoints
        binding.stressBar.progress = DataBaseRepository.stressPoints
        val resultPer: Int = (DataBaseRepository.stressPoints * 100) / 25
        binding.resultNum.setText("$resultPer")
        binding.resultNum.append("%")
        binding.backButt.setOnClickListener {
            (requireActivity() as MainActivity).router.openMood()
        }
        return binding.root
    }
}
