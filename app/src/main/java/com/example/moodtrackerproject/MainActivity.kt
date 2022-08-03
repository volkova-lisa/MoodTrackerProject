package com.example.moodtrackerproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.moodtrackerproject.databinding.ActivityMainBinding
import com.example.moodtrackerproject.routing.Router
import com.example.moodtrackerproject.ui.BaseActivity
import com.example.moodtrackerproject.utils.PreferenceManager

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getActivityBinding(inflater: LayoutInflater) = ActivityMainBinding.inflate(inflater)
    // TODO("revise router use")
    val router = Router(this)

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
        binding?.run {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController: NavController = navHostFragment.navController
            bottomNavigation.setupWithNavController(navController)
            router.setNavigationController(navController)

            if (PreferenceManager.getPreference(this@MainActivity).getInitUser()) {
                navController.graph.setStartDestination(R.id.homeFragment)
                router.openHome()
            } else {
                navController.graph.setStartDestination(R.id.welcomeFragment)
                router.openWelcome()
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomNavigation.visibility = when (destination.id) {
                    R.id.loginFragment -> View.GONE
                    R.id.welcomeFragment -> View.GONE
                    R.id.notesFragment -> View.VISIBLE
                    else -> View.VISIBLE
                }
            }
        }
    }
}
