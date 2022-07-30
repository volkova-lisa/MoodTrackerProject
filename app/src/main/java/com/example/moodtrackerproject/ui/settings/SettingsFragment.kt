package com.example.moodtrackerproject.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentSettingsBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.utils.click
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
        if (::props.isInitialized) {
            props.fetchSettings()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            langSwitch.isChecked = DataBaseRepository.getLang() == "en"
        }
    }

    override fun render(props: SettingsProps) {
        this.props = props
        binding?.run {

            name.text = props.name
            emailSett.text = props.email
            // check first which lang is it
            langSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
                if (onSwitch) {
                    if (props.language != "en") Lingver.getInstance()
                        .setLocale(requireContext(), "en")
                    else Lingver.getInstance().setLocale(requireContext(), "ua")
                    props.saveLang("en")

                    enTitle.setTextColor(resources.getColor(R.color.light_purple))
                    uaTitle.setTextColor(resources.getColor(R.color.text_grey))
                } else {
                    if (props.language != "en") Lingver.getInstance()
                        .setLocale(requireContext(), "en")
                    else Lingver.getInstance().setLocale(requireContext(), "ua")
                    props.saveLang("ua")

                    uaTitle.setTextColor(resources.getColor(R.color.light_purple))
                    enTitle.setTextColor(resources.getColor(R.color.text_grey))
                }
            }
            darkModeSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
                if (onSwitch) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    props.saveMode(true)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    props.saveMode(false)
                }
            }

            editAccButton.click({
                val builder = context?.let { AlertDialog.Builder(it) }
                val dialogLayout = layoutInflater.inflate(R.layout.edit_profile_layout, null)
                val editName = dialogLayout.findViewById<EditText>(R.id.name_edit)

                with(builder) {
                    this?.setPositiveButton("Save") { dialog, which ->
                        name.text = editName.text.toString()
                    }
                    this?.setNegativeButton("Cancel") { dialog, which ->
                        Toast.makeText(context, getString(R.string.not_saved), Toast.LENGTH_SHORT)
                            .show()
                    }
                    this?.setView(dialogLayout)
                    this?.show()
                }
            })
        }
    }
}
