package com.example.moodtrackerproject.ui.login

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentLoginScreenBinding
import com.example.moodtrackerproject.routing.Routes
import com.example.moodtrackerproject.ui.login.LoginAction.*
import com.example.moodtrackerproject.ui.login.LoginError.*
import com.example.moodtrackerproject.ui.notes.NotesFragment
import com.example.moodtrackerproject.ui.registration.RegistrationFragment
import com.example.moodtrackerproject.ui.reset.ResetPasswordFragment
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginScreenBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            createNewAccountTextButton.setOnClickListener {
                Routes.getInstance(requireActivity() as MainActivity).goTo(requireActivity(), RegistrationFragment())
            }
            loginButton.setOnClickListener {
                removeInputsErrors()
                viewModel.checkLogInData(
                    binding.emailInput.text.toString(),
                    binding.passInput.text.toString()
                )
            }
            pass.setEndIconOnClickListener {
                var timesPressed = true
                // ("//how to change it normally?")
                passInput.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                if (passInput.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    // set icon
                } else {
                    // set icon
                }
            }
        }
        viewModel.liveData.observe(viewLifecycleOwner, {
            render(it)
        })
    }
    private fun render(state: LoginViewState) {
        showProgress(state.isLoading)
        state.action?.let { handleAction(it) }
        state.error?.let { handleError(it) }
    }
    private fun showProgress(isLoading: Boolean) {
        if (isLoading) {
            // ("//to start progress bar")
            binding.run {
                progressBar.isVisible = true
                loginButton.text = ""
            }
        } else {
            binding.run {
                progressBar.isVisible = false
            }
        }
    }
    private fun removeInputsErrors() {
        binding.run {
            emailInput.error = null
            passInput.error = null
        }
    }
    private fun handleError(loginError: LoginError) {
        when (loginError) {
            is ShowNoInternet -> showNoInternetError()
            is ShowPasswordInvalid -> binding.passInput.error = getString(R.string.invalid_password)
            is ShowEmailInvalid -> binding.emailInput.error = getString(R.string.invalid_email)
        }
    }
    private fun handleAction(loginAction: LoginAction) {
        when (loginAction) {
            is StartNotesScreen -> {
//                Routes.getInstance(requireActivity() as MainActivity)
//                    .goTo(requireActivity(), NotesFragment())
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.nav_host_fragment, NotesFragment())
                transaction.commit()
            }
            is StartRegistrationScreen -> {
                Routes.getInstance(requireActivity() as MainActivity)
                    .goTo(requireActivity(), LoginFragment())
            }
            is StartResetPasswordScreen -> {
                Routes.getInstance(requireActivity() as MainActivity).goTo(
                    requireActivity(),
                    ResetPasswordFragment()
                )
            }
        }
    }
    private fun showNoInternetError() {
        Snackbar.make(
            binding.loginScreen,
            resources.getString(R.string.no_internet_connection_warning),
            Snackbar.LENGTH_LONG
        ).show()
    }
}
