package com.example.moodtrackerproject.ui.registration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentRegistrationBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.registration.RegistrationProps.RegistrationAction
import com.example.moodtrackerproject.ui.registration.RegistrationProps.RegistrationAction.StartLogInScreen
import com.example.moodtrackerproject.ui.registration.RegistrationProps.RegistrationError
import com.example.moodtrackerproject.utils.click

class RegistrationFragment : BaseFragment<RegistrationViewModel, FragmentRegistrationBinding, RegistrationProps>(
    RegistrationViewModel::class.java
) {

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentRegistrationBinding = FragmentRegistrationBinding.inflate(inflater, container, false)

    @Suppress("DEPRECATION")
    override fun render(props: RegistrationProps) {
        binding?.run {
            progressBar.isVisible = props.isLoading
            pass.isPasswordVisibilityToggleEnabled = props.isLoading
            registerButton.text = if (props.isLoading) "" else getString(R.string.create_account)
            registerButton.click({
                props.checkRegistrationData(
                    emailInput.text.toString(),
                    passInput.text.toString(),
                    nameInput.text.toString()
                )
                props.saveAccData(nameInput.text.toString(), emailInput.text.toString())
            })
            alreadyHaveTextButton.click(props.openLogin)
            props.action?.let { handleAction(it) }
            props.error?.let { handleError(it) }
        }
    }

    @Suppress("DEPRECATION")
    private fun FragmentRegistrationBinding.handleError(registrationError: RegistrationError) {
        when (registrationError) {
            is RegistrationError.ShowPasswordInvalid -> {
                pass.isPasswordVisibilityToggleEnabled = false
                passInput.error = getString(R.string.invalid_password)
            }
            is RegistrationError.ShowEmailInvalid -> {
                emailInput.error = getString(R.string.invalid_email)
            }
            is RegistrationError.ShowRegistrationError -> {
                emailInput.error = getString(R.string.email_address_collision)
            }
            is RegistrationError.ShowNameInvalid -> {
                nameInput.error = getString(R.string.add_name)
            }
        }
    }

    private fun handleAction(action: RegistrationAction) {
        when (action) {
            is StartLogInScreen -> {
                (requireActivity() as MainActivity).router.openLoginFromRegistration()
            }
        }
    }
}
