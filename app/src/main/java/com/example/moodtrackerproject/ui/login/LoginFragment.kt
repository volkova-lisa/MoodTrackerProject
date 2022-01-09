package com.example.moodtrackerproject.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentLoginScreenBinding
import com.example.moodtrackerproject.ui.login.LoginAction.StartNotesScreen
import com.example.moodtrackerproject.ui.login.LoginAction.StartRegistrationScreen
import com.example.moodtrackerproject.ui.login.LoginAction.StartResetPasswordScreen
import com.example.moodtrackerproject.ui.login.LoginError.ShowEmailInvalid
import com.example.moodtrackerproject.ui.login.LoginError.ShowNoInternet
import com.example.moodtrackerproject.ui.login.LoginError.ShowPasswordInvalid
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginScreenBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginScreenBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            createNewAccountTextButton.setOnClickListener {
                (requireActivity() as MainActivity).router.openRegistration()
            }
            loginButton.setOnClickListener {
                removeInputsErrors()
                viewModel.checkLogInData(
                    binding.emailInput.text.toString(),
                    binding.passInput.text.toString()
                )
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
                (requireActivity() as MainActivity).router.openHome()
            }
            is StartRegistrationScreen -> {
                (requireActivity() as MainActivity).router.openRegistration()
            }
            is StartResetPasswordScreen -> {
                (requireActivity() as MainActivity).router.openResetPassScreen()
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
