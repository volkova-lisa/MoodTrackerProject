package com.example.moodtrackerproject.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentLoginScreenBinding
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginScreenBinding.inflate(layoutInflater, container, false)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.createNewAccountButton.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, RegistrationFragment())
            transaction.commit()
        }
        login()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
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
