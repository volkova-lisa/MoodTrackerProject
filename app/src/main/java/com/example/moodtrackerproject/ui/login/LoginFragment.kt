package com.example.moodtrackerproject.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentLoginScreenBinding
import com.example.moodtrackerproject.routing.Routes
import com.example.moodtrackerproject.ui.NotesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private val binding get() = _binding!!
    lateinit var auth: FirebaseAuth
    lateinit var navBar: BottomNavigationView

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginScreenBinding.inflate(layoutInflater, container, false)
        auth = FirebaseAuth.getInstance()
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

            auth.signInWithEmailAndPassword(binding.emailInput.text.toString(), binding.passInput.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Routes.goTo(requireActivity(), NotesFragment())
                    } else Toast.makeText(context, getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
                }
        }
    }
}
