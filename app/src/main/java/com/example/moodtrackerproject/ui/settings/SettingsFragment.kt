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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            langSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
                if (onSwitch) {
                    Lingver.getInstance().setLocale(requireContext(), "en")
                    enTitle.setTextColor(resources.getColor(R.color.light_purple))
                    uaTitle.setTextColor(resources.getColor(R.color.text_grey))
                } else {
                    Lingver.getInstance().setLocale(requireContext(), "uk-rUA")
                    uaTitle.setTextColor(resources.getColor(R.color.light_purple))
                    enTitle.setTextColor(resources.getColor(R.color.text_grey))
                }
            }
            darkModeSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
                if (onSwitch) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }

            editAccButton.click({
                val builder = context?.let { AlertDialog.Builder(it) }
                val dialogLayout = layoutInflater.inflate(R.layout.edit_profile_layout, null)
                // val positiveButton = dialogLayout.findViewById<Button>(R.id.save_button)
                // val negativeButton = dialogLayout.findViewById<Button>(R.id.cancel_button)
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

    override fun render(props: SettingsProps) {
        binding?.run {
            langSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
            }
        }
    }
}
