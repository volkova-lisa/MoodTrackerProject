package com.example.moodtrackerproject.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.databinding.FragmentRegistrationBinding
import com.example.moodtrackerproject.routing.Routes
import com.google.android.material.bottomnavigation.BottomNavigationView

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    lateinit var navBar: BottomNavigationView

    private val registrationViewModel: RegistrationViewModel by lazy {
        ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        registration()
        binding.alreadyHaveTextButton.setOnClickListener {
            Routes.goTo(requireActivity(), LoginFragment())
        }
        return binding.root
    }

    private fun registration() {
        binding.registerButton.setOnClickListener {
            registrationViewModel.checkRegistrationData(
                binding.emailInput.text.toString(),
                binding.passInput.text.toString(),
                binding.nameInput.text.toString(),
                binding,
                requireContext()
            )
            registrationViewModel.commitRegistration(binding, requireActivity(), requireContext())
        }
    }
}
