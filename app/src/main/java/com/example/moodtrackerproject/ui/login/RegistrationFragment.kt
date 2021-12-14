package com.example.moodtrackerproject.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentRegistrationBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationFragment : Fragment() {

    lateinit var auth: FirebaseAuth
    private var _binding: FragmentRegistrationBinding? = null
    private val mBinding get() = _binding!!
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    lateinit var navBar: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")
        registration()
        return mBinding.root
    }

    private fun registration() {
        mBinding.registerButton.setOnClickListener {
            val inputEmail = mBinding.emailInput.text.toString()
            val inputPass = mBinding.passInput.text.toString()
            val inputName = mBinding.nameInput.text.toString()

            if (TextUtils.isEmpty(inputEmail)) {
                mBinding.emailInput.error = getString(R.string.registration_enter_email)
                return@setOnClickListener
            } else if (TextUtils.isEmpty(inputPass)) {
                mBinding.passInput.error = getString(R.string.registration_enter_pass)
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(inputEmail, inputPass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val currentUser = auth.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child("name")?.setValue(inputName)
                        Toast.makeText(context, getString(R.string.reg_successful), Toast.LENGTH_SHORT).show()
                        val transaction = requireActivity().supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.nav_host_fragment, LoginFragment())
                        transaction.commit()
                    } else {
                        Toast.makeText(context, getString(R.string.reg_failed), Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
