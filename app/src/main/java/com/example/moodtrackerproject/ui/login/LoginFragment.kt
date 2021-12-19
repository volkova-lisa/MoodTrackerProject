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
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import java.lang.Exception

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

        pickDataForLogin()

        loginViewModel.loginOutputLiveData.observe(
            requireActivity(),
            { output -> showLoginOutput(output) }
        )
        loginViewModel.loginStateLiveData.observe(requireActivity(), { state -> updateUi(state) })

        binding.pass.setEndIconOnClickListener {
            var timesPressed = true
            TODO("//how to change it normally?")
        }
        return binding.root
    }
    private fun updateUi(state: LoginViewState) {
        showProgress(state.isLoading)
    }
    private fun showProgress(isLoading: Boolean) {
        if (isLoading) {
            TODO("//to start progress bar")
        } else {
            // to stop??
        }
    }

    private fun pickDataForLogin() {
        binding.loginButton.setOnClickListener {
            removeInputsErrors()
            loginViewModel.checkLogInData(
                binding.emailInput.text.toString(),
                binding.passInput.text.toString()
            )
        }
    }

    private fun removeInputsErrors() {
        binding.emailInput.error = ""
        binding.passInput.error = ""
    }

    private fun showLoginOutput(loginOutput: LoginOutputs) {
        when (loginOutput) {
            is LoginOutputs.StartLogInScreen -> Routes.goTo(requireActivity(), LoginFragment())
            is LoginOutputs.StartResetPasswordScreen -> Routes.goTo(requireActivity(), ResetPasswordFragment())
            is LoginOutputs.ShowNoInternet -> showNoInternetError()
            is LoginOutputs.ShowPasswordInvalid -> binding.passInput.error = "Invalid Password"
            is LoginOutputs.ShowEmailInvalid -> binding.emailInput.error = "Invalid Email"
            is LoginOutputs.ShowLoginError -> showLoginError(loginOutput.exception)
        }
    }

    private fun showLoginError(exception: Exception?) {
        val errorMessage = when (exception) {
            is FirebaseAuthUserCollisionException -> {
                resources.getString(R.string.email_address_collision)
            }
            else -> {
                resources.getString(R.string.unknown_error)
            }
        }
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showNoInternetError() {
        Snackbar.make(
            binding.loginScreen,
            resources.getString(R.string.no_internet_connection_warning),
            Snackbar.LENGTH_LONG
        ).show()
    }
}
