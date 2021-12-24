package com.example.moodtrackerproject

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.moodtrackerproject.databinding.ActivityMainBinding
import com.example.moodtrackerproject.routing.Router
import com.example.moodtrackerproject.utils.Preference

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    val router = Router(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        router.setNavigationController(navController)

        if (Preference.getPreference(this).getInitUser()) {
            navController.graph.startDestination = R.id.homeFragment
//            router.openHome()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d("HELLO", destination.toString())
            binding.bottomNavigation.visibility = when (destination.id) {
                R.id.loginFragment -> View.GONE
                R.id.welcomeFragment -> View.GONE
                R.id.notesFragment -> View.VISIBLE
                else -> View.VISIBLE
            }
        }
    }
}
