package com.example.moodtrackerproject.ui.mood.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.databinding.FragmentAddMoodBinding

class AddMoodFragment : Fragment() {

    private lateinit var binding: FragmentAddMoodBinding
    private val viewModel: AddMoodViewModel by lazy {
        ViewModelProvider(this).get(AddMoodViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddMoodBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
