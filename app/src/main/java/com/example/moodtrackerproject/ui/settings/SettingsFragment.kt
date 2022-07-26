package com.example.moodtrackerproject.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moodtrackerproject.databinding.FragmentSettingsBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.yariksoffice.lingver.Lingver
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
        Log.d("onResume -------", resources.configuration.locale.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("before -------", resources.configuration.locale.toString())
        binding?.run {
            langSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
                if (onSwitch) {
                    Lingver.getInstance().setLocale(requireContext(), "en")
                    // fragmentManager.beginTransaction().replace(R.id.settingsFragment, fragm).commitAllowingStateLoss()
                } else Lingver.getInstance().setLocale(requireContext(), "ua")
            }
            Log.d("after -------", resources.configuration.locale.toString())
        }
    }

    override fun render(props: SettingsProps) {
        Log.d("before loadLocate-------", resources.configuration.locale.toString())

        binding?.run {
            langSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
            }
            Log.d("after in render -------", resources.configuration.locale.toString())
        }
    }
}
