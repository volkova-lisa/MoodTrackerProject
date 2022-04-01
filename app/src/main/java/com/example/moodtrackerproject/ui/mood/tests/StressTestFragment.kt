package com.example.moodtrackerproject.ui.mood.tests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.databinding.FragmentStressTestBinding

class StressTestFragment : Fragment() {

    private lateinit var binding: FragmentStressTestBinding
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
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }
}
