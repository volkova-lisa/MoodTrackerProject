package com.example.moodtrackerproject.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.databinding.FragmentLoginScreenBinding
import com.example.moodtrackerproject.routing.Routes

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginScreenBinding.inflate(layoutInflater, container, false)
        binding.createNewAccountTextButton.setOnClickListener {
            Routes.goTo(requireActivity(), RegistrationFragment())
        }
        login()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        // initialisation()
    }

    private fun initialisation() {
        TODO("Not yet implemented")
    }

    private fun login() {
        binding.loginButton.setOnClickListener {
            loginViewModel.checkLogInData(
                binding.emailInput.text.toString(),
                binding.passInput.text.toString(),
                binding,
                requireContext()
            )
            loginViewModel.commitLogIn(binding, requireActivity(), requireContext())
        }
    }
}
