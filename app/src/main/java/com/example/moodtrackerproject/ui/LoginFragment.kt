package com.example.moodtrackerproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentLoginScreenBinding
import com.example.moodtrackerproject.databinding.FragmentWelcomeScreenBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private val mBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginScreenBinding.inflate(layoutInflater, container, false)
        return inflater.inflate(R.layout.fragment_login_screen, container, false)
    }
}
