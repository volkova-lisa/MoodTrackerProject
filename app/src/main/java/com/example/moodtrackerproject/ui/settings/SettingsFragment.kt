package com.example.moodtrackerproject.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moodtrackerproject.R
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            langSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
//                val sharedPreferences = (requireActivity() as MainActivity).getSharedPreferences("Settings", Activity.MODE_PRIVATE)
//                val language = sharedPreferences.getString("My_Lang", "") ?: "es"
//                Lingver.getInstance().setLocale(requireContext(), language)
//                Log.d("pref -------=", language.toString())

                if (onSwitch) {
                    Lingver.getInstance().setLocale(requireContext(), "en")
                    enTitle.setTextColor(resources.getColor(R.color.light_purple))
                    uaTitle.setTextColor(resources.getColor(R.color.text_grey))
//                    val editor = (requireActivity() as MainActivity).getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
//                    editor.putString("My_Lang", "en")
//                    editor.apply()
//                    (requireActivity() as MainActivity).recreate()

                    // fragmentManager.beginTransaction().replace(R.id.settingsFragment, fragm).commitAllowingStateLoss()
                } else {
                    Lingver.getInstance().setLocale(requireContext(), "uk-rUA")
                    uaTitle.setTextColor(resources.getColor(R.color.light_purple))
                    enTitle.setTextColor(resources.getColor(R.color.text_grey))
//                    val editor = (requireActivity() as MainActivity).getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
//                    editor.putString("My_Lang", "uk-rUA")
//                    editor.apply()
//                    (requireActivity() as MainActivity).recreate()
                }
                // Log.d("pref -------", language.toString())
            }
        }
    }

    override fun render(props: SettingsProps) {
        binding?.run {
            langSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
            }
        }
    }
}
