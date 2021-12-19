package com.example.moodtrackerproject.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentRegistrationBinding
import com.example.moodtrackerproject.routing.Routes
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import java.lang.Exception

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private val registrationViewModel: RegistrationViewModel by lazy {
        ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)

        pickDataForRegistration()

        binding.alreadyHaveTextButton.setOnClickListener {
            Routes.goTo(requireActivity(), LoginFragment())
        }
        registrationViewModel.registrationOutputLiveData.observe(
            requireActivity(),
            { output -> showRegistrationOutput(output) }
        )
        registrationViewModel.registrationStateLiveData.observe(
            requireActivity(),
            { state -> updateUi(state) }
        )

        binding.pass.setEndIconOnClickListener {
            var timesPressed = true
            // ("//how to change it normally?")
        }
        return binding.root
    }

    private fun updateUi(state: RegistrationViewState) {
        showProgress(state.isLoading)
    }
    private fun showProgress(isLoading: Boolean) {
        if (isLoading) {
            // ("//to start progress bar")
        } else {
            // to stop??
        }
    }

    private fun pickDataForRegistration() {
        binding.registerButton.setOnClickListener {
            removeInputsErrors()
            registrationViewModel.checkRegistrationData(
                binding.emailInput.text.toString(),
                binding.passInput.text.toString(),
                binding.nameInput.text.toString()
            )
        }
    }

    private fun removeInputsErrors() {
        binding.emailInput.error = ""
        binding.passInput.error = ""
    }

    private fun showRegistrationOutput(registrationOutput: RegistrationOutputs) {
        when (registrationOutput) {
            is StartLogInScreen -> {
                Routes.goTo(requireActivity(), LoginFragment())
                Log.d("", "AAAAAAAAAAAAAAAAAAAAAA")
            }
            is StartResetPasswordScreen -> {
                Routes.goTo(requireActivity(), ResetPasswordFragment())
                Log.d("", "BBBBBBBBBBBBBBBBBBBBBB")
            }
            is ShowNoInternet -> {
                showNoInternetError()
                Log.d("", "CCCCCCCCCCCCCCCCCCCCCCC")
            }
            is ShowPasswordInvalid -> {
                binding.passInput.error = "Invalid Password"
                Log.d("", "DDDDDDDDDDDDDDDDDDDDDD")
            }
            is ShowEmailInvalid -> {
                binding.emailInput.error = "Invalid Email"
                Log.d("", "EEEEEEEEEEEEEEEEEEEEEE")
            }
            is ShowRegistrationError -> {
                showLoginError(registrationOutput.exception)
                Log.d("", "DDDDDDDDDDDDDDDDDDDDDDD")
            }
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
            binding.registerScreen,
            resources.getString(R.string.no_internet_connection_warning),
            Snackbar.LENGTH_LONG
        ).show()
    }
}
