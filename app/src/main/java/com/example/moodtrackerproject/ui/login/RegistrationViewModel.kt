package com.example.moodtrackerproject.ui.login

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentRegistrationBinding
import com.example.moodtrackerproject.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationViewModel : ViewModel() {
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    private lateinit var auth: FirebaseAuth

    fun checkRegistrationData(email: String, password: String, fullName: String, binding: FragmentRegistrationBinding, context: Context) {
        var isValid = true
        if (!fullName.isEmpty()) {
            binding.nameInput.error = context.getString(R.string.enter_name)
            isValid = false
        } else {
            FULL_NAME = fullName
        }

        if (!email.isEmpty()) {
            if (!email.isEmailValid()) {
                binding.emailInput.error = context.getString(R.string.registration_enter_email)
                isValid = false
            } else {
                EMAIL = email
            }
        }

        if (!password.isEmpty()) {
            if (!password.isPasswordValid()) {
                binding.passInput.error = context.getString(R.string.registration_enter_pass)
                isValid = false
            } else {
                PASSWORD = password
            }
        }
    }

    fun commitRegistration(binding: FragmentRegistrationBinding, fragmentActivity: FragmentActivity, context: Context) {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child(PROFILE)

        auth.createUserWithEmailAndPassword(binding.emailInput.text.toString(), binding.passInput.text.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val currentUser = auth.currentUser
                    val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                    currentUserDb?.child(NAME)?.setValue(binding.nameInput.text.toString())
                    Toast.makeText(context, context.getString(R.string.reg_successful), Toast.LENGTH_SHORT).show()
                    val transaction = fragmentActivity.supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, LoginFragment())
                    transaction.commit()
                } else {
                    Toast.makeText(context, context.getString(R.string.reg_failed), Toast.LENGTH_SHORT).show()
                }
            }
    }
}
