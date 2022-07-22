package com.example.moodtrackerproject.ui.settings

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentSettingsBinding
import com.example.moodtrackerproject.ui.BaseFragment
import java.util.*

class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding, SettingsProps>(
    SettingsViewModel::class.java
) {

    private lateinit var props: SettingsProps

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)

    override fun onResume() {
        super.onResume()
        loadLocate()
        Log.d("onResume -------", resources.configuration.locale.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadLocate()
        Log.d("before -------", resources.configuration.locale.toString())
        binding?.run {
            langSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
                if (onSwitch) {
                    setLocate("uk-rUA")
                    // fragmentManager.beginTransaction().replace(R.id.settingsFragment, fragm).commitAllowingStateLoss()
                } else setLocate("en")
            }
            Log.d("after -------", resources.configuration.locale.toString())
        }
    }

    override fun render(props: SettingsProps) {
        Log.d("before loadLocate-------", resources.configuration.locale.toString())

        loadLocate()
        binding?.run {
            langSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
                if (onSwitch) setLocate("uk-rUA")
                else setLocate("en")
            }
            Log.d("after in render -------", resources.configuration.locale.toString())
        }
    }

    private fun setLocate(Lang: String) {
        Log.d("-------", resources.configuration.locale.toString())

        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        (requireActivity() as MainActivity).resources.updateConfiguration(config, (requireActivity() as MainActivity).resources.displayMetrics)
        val editor = (requireActivity() as MainActivity).getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun loadLocate() {
        val sharedPreferences = (requireActivity() as MainActivity).getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        Log.d("+++++++++", language.toString())

        setLocate(language!!)
    }
}
