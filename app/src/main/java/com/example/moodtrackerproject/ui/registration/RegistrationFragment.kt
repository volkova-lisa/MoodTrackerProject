package com.example.moodtrackerproject.ui.registration

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentRegistrationBinding
import com.example.moodtrackerproject.routing.Routes
import com.example.moodtrackerproject.ui.login.LoginFragment
import com.example.moodtrackerproject.ui.registration.RegistrationAction.*
import com.example.moodtrackerproject.ui.registration.RegistrationError.*
import com.example.moodtrackerproject.ui.reset.ResetPasswordFragment
import com.google.android.material.snackbar.Snackbar

class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding

    private val viewModel: RegistrationViewModel by lazy {
        ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            registerButton.setOnClickListener {
                removeInputsErrors()
                viewModel.checkRegistrationData(
                    emailInput.text.toString(),
                    passInput.text.toString(),
                    nameInput.text.toString()
                )
            }
            alreadyHaveTextButton.setOnClickListener {
                Routes.goTo(requireActivity(), LoginFragment())
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

    private fun render(state: RegistrationViewState) {
        showProgress(state.isLoading)
        state.action?.let { handleAction(it) }
        state.error?.let { handleError(it) }
    }

    private fun showProgress(isLoading: Boolean) {
        if (isLoading) {
            // ("//to start progress bar")
            binding.run {
                progressBar.isVisible = true
                registerButton.text = ""
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

    private fun handleError(registrationError: RegistrationError) {
        when (registrationError) {
            is ShowNoInternet -> {
                showNoInternetError()
            }
            is ShowPasswordInvalid -> {
                binding.passInput.error = getString(R.string.invalid_password)
            }
            is ShowEmailInvalid -> {
                binding.emailInput.error = getString(R.string.invalid_email)
            }
            is ShowRegistrationError -> {
                binding.emailInput.error = getString(R.string.email_address_collision)
            }
            is ShowNameInvalid -> {
                binding.nameInput.error = getString(R.string.add_name)
            }
        }
    }

    private fun handleAction(registrationAction: RegistrationAction) {
        when (registrationAction) {
            is StartLogInScreen -> {
                Routes.goTo(requireActivity(), LoginFragment())
            }
            is StartResetPasswordScreen -> {
                Routes.goTo(requireActivity(), ResetPasswordFragment())
                // https://github.com/JakeWharton/timber
            }
        }
    }

    private fun showNoInternetError() {
        view?.let {
            Snackbar.make(
                it,
                resources.getString(R.string.no_internet_connection_warning),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
