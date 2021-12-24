package com.example.moodtrackerproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentWelcomeScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class WelcomeFragment : Fragment() {

    // TODO("this needs to be removed from the fragment and should be
    //  handled with LiveData")
    private var _binding: FragmentWelcomeScreenBinding? = null

    private val binding get() = _binding!!
    lateinit var navBar: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeScreenBinding.inflate(layoutInflater, container, false)

        binding.welcomeButton.setOnClickListener {
//            Router.getInstance(requireActivity() as MainActivity).goTo(requireActivity(), LoginFragment())
            (requireActivity() as MainActivity).router.openLoginFromWelcome()
        }

        return binding.root
    }
}
