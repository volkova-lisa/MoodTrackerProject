package com.example.moodtrackerproject.ui.mood.tests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentTestResultsBinding

class TestResults : Fragment() {

    private lateinit var binding: FragmentTestResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestResultsBinding.inflate(layoutInflater, container, false)
        binding.stressBar.max = 25
        binding.stressBar.progress = DataBaseRepository.points
        val resultPer: Int = (DataBaseRepository.points * 100) / 25
        binding.resultNum.text = "---"
        return inflater.inflate(R.layout.fragment_test_results, container, false)
    }
}
