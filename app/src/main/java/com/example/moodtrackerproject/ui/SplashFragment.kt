package com.example.moodtrackerproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val mBinding get() = _binding!!
    val splashScreenScope = CoroutineScope(Dispatchers.Main) // should it be Main??

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashScreenBinding.inflate(layoutInflater, container, false)

        // is splash screen correct?
        splashScreenScope.launch {
            delay(2000)
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, WelcomeFragment())
            transaction.commit()
        }

        return mBinding.root
    }
}
