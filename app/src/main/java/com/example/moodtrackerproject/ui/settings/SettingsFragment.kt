package com.example.moodtrackerproject.ui.settings

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentSettingsBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.utils.click
import com.example.moodtrackerproject.utils.convertToBitmap
import com.example.moodtrackerproject.utils.convertToString
import com.yariksoffice.lingver.Lingver

class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding, SettingsProps>(
    SettingsViewModel::class.java
) {

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }

    private lateinit var props: SettingsProps
    private lateinit var imageBitmap: Bitmap
    private lateinit var onActivityResultImageBitmap: Bitmap
    private lateinit var photoAlertDialog: ImageView

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
            langSwitch.isChecked = Lingver.getInstance().getLanguage() == "en"
            // langSwitch.isChecked = DataBaseRepository.getLang() == "en"
        }
    }

    override fun render(props: SettingsProps) {
        this.props = props
        binding?.run {
            val currLang = Lingver.getInstance().getLanguage()
            Log.d("===HELLO", "render called")
            imageBitmap = props.photo.convertToBitmap(resources)
            photo.setImageBitmap(imageBitmap)
            name.text = props.name
            emailSett.text = props.email
            langSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
                if (onSwitch) {
                    if (currLang == "en") Lingver.getInstance()
                        .setLocale(requireContext(), "ua")
                    else Lingver.getInstance().setLocale(requireContext(), "en")
                    props.saveLang(currLang)

                    enTitle.setTextColor(resources.getColor(R.color.light_purple))
                    uaTitle.setTextColor(resources.getColor(R.color.text_grey))
                } else {
                    if (currLang == "ua") Lingver.getInstance()
                        .setLocale(requireContext(), "en")
                    else Lingver.getInstance().setLocale(requireContext(), "ua")
                    props.saveLang(currLang)

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
                photoAlertDialog = dialogLayout.findViewById(R.id.photo)
                photoAlertDialog.setImageBitmap(props.photo.convertToBitmap(resources))
                Log.d("========HELLO", imageBitmap.convertToString())
                photoAlertDialog.click({
                    uploadImageGallery()
                })
                editName.setText(props.name)
                with(builder) {
                    this?.setPositiveButton("Save") { dialog, which ->
                        name.text = editName.text.toString()
                        imageBitmap = onActivityResultImageBitmap
                        props.saveName(editName.text.toString())
                        props.savePhoto(onActivityResultImageBitmap.convertToString())
                        props.fetchSettings()
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

    private fun uploadImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            onActivityResultImageBitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver, data?.data!!)).copy(
                Bitmap.Config.RGBA_F16, true
            )
            photoAlertDialog.setImageBitmap(onActivityResultImageBitmap)
        } else Toast.makeText(context, "Nonono", Toast.LENGTH_SHORT).show()
    }
}
