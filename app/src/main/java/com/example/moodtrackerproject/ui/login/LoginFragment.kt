package com.example.moodtrackerproject.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentLoginScreenBinding
import com.example.moodtrackerproject.ui.NotesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private val mBinding get() = _binding!!
    lateinit var auth: FirebaseAuth
    lateinit var navBar: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginScreenBinding.inflate(layoutInflater, container, false)
        navBar = requireActivity()!!.findViewById(R.id.bottom_navigation)
        navBar.isVisible = false
        auth = FirebaseAuth.getInstance()
        mBinding.createNewAccountButton.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, RegistrationFragment())
            transaction.commit()
        }
        login()
        return mBinding.root
    }

    private fun login() {
        mBinding.loginButton.setOnClickListener {
            val emailInput = mBinding.emailInput.text.toString()
            val passInput = mBinding.passInput.text.toString()
            if (TextUtils.isEmpty(emailInput)) {
                mBinding.emailInput.error = "Please enter email"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(passInput)) {
                mBinding.passInput.error = "Please enter password"
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(emailInput, passInput)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val transaction = requireActivity().supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.nav_host_fragment, NotesFragment())
                        transaction.commit()
                    } else Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
