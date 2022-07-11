package com.example.moodtrackerproject.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moodtrackerproject.databinding.FragmentSettingsBinding
import com.example.moodtrackerproject.ui.BaseFragment

class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding, SettingsProps>(
    SettingsViewModel::class.java
) {

    private lateinit var props: SettingsProps

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)

    override fun render(props: SettingsProps) {
        TODO("Not yet implemented")
    }
}
