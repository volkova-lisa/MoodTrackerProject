package com.example.moodtrackerproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.moodtrackerproject.databinding.ActivityMainBinding
import com.example.moodtrackerproject.utils.Preference

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        setContentView(binding.root)

        Preference.getPreference(this)

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            Log.d("HELLO", destination.toString())
//            when (destination.id) {
//                R.id.loginFragment -> binding.bottomNavigation.visibility = View.GONE
//                R.id.welcomeFragment -> binding.bottomNavigation.visibility = View.GONE
//                R.id.notesFragment -> binding.bottomNavigation.visibility = View.VISIBLE
//            }
//        }

//        if(!Preference.getInitUser()){
//            navController.navigate()
//        }
    }
}
