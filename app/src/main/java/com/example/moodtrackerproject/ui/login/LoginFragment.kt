package com.example.moodtrackerproject.ui.login

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.app.Store
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentLoginScreenBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.login.LoginProps.LoginAction
import com.example.moodtrackerproject.ui.login.LoginProps.LoginError
import com.example.moodtrackerproject.utils.click
import com.example.moodtrackerproject.utils.snackBar

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginScreenBinding, LoginProps>(
    LoginViewModel::class.java
) {

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentLoginScreenBinding = FragmentLoginScreenBinding.inflate(inflater, container, false)

    override fun render(props: LoginProps) {
        binding?.run {
//            if (BuildConfig.DEBUG) {
//                emailInput.setText(BuildConfig.USERNAME)
//                passInput.setText(BuildConfig.PASSWORD)
//            }
            progressBar.isVisible = props.isLoading
            loginButton.text = if (props.isLoading) "" else getString(R.string.login_screen_login)

            createNewAccountTextButton.click(props.openRegistration)
            forgotPassTextButton.click(props.openResetPassword)
            loginButton.click({
                emailInput.error = null
                passInput.error = null
                props.checkLogInData(emailInput.text.toString(), passInput.text.toString())
                Store.setState(Store.appState.homeState.copy(name = DataBaseRepository.getName()))
                Log.d("SET FROM LOG ----", "")
            }, 100)
            props.action?.let { handleAction(it) }
            props.error?.let { handleError(it) }
        }
    }

    @Suppress("DEPRECATION")
    private fun FragmentLoginScreenBinding.handleError(loginError: LoginError) {
        when (loginError) {
            is LoginError.ShowNoInternet -> requireActivity().snackBar(getString(R.string.no_internet_connection_warning))
            is LoginError.ShowPasswordInvalid -> {
                pass.isPasswordVisibilityToggleEnabled = false
                passInput.error = getString(R.string.invalid_password)
            }
            is LoginError.ShowEmailInvalid ->
                emailInput.error = getString(R.string.invalid_email)
        }
    }

    private fun handleAction(loginAction: LoginAction) {
        when (loginAction) {
            is LoginAction.StartNotesScreen -> {
                (requireActivity() as MainActivity).router.openHome()
            }
            is LoginAction.StartRegistrationScreen -> {
                (requireActivity() as MainActivity).router.openRegistration()
            }
            is LoginAction.StartResetPasswordScreen -> {
                (requireActivity() as MainActivity).router.openResetPassScreen()
            }
        }
    }
}
