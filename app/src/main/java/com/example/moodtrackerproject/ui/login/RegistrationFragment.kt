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
import com.example.moodtrackerproject.utils.NAME
import com.example.moodtrackerproject.utils.PROFILE
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationFragment : Fragment() {

    lateinit var auth: FirebaseAuth
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null
    lateinit var navBar: BottomNavigationView

    private lateinit var registrationViewModel: RegistrationViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child(PROFILE)
        registration()
        return binding.root
    }

    private fun registration() {
        binding.registerButton.setOnClickListener {

            registrationViewModel.checkRegistrationData(
                binding.emailInput.text.toString(),
                binding.passInput.text.toString(),
                binding.nameInput.text.toString(),
                binding,
                requireContext()
            )

            auth.createUserWithEmailAndPassword(binding.emailInput.text.toString(), binding.passInput.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val currentUser = auth.currentUser
                        val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child(NAME)?.setValue(binding.nameInput.text.toString())
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
