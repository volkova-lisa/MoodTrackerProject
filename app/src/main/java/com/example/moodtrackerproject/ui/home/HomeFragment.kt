package com.example.moodtrackerproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentHomeBinding
import com.example.moodtrackerproject.databinding.FragmentLoginScreenBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.run{
            logout.setOnClickListener{

            }
        }


        return binding.root
    }
}
