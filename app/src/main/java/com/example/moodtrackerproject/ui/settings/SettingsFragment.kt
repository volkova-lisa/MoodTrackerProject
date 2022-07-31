package com.example.moodtrackerproject.ui.settings

import android.R
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Bundle
import android.util.Base64
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
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentSettingsBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.utils.click
import com.yariksoffice.lingver.Lingver
import java.io.ByteArrayOutputStream


class SettingsFragment : BaseFragment<SettingsViewModel, FragmentSettingsBinding, SettingsProps>(
    SettingsViewModel::class.java
) {

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }

    private lateinit var props: SettingsProps
    lateinit var imageBitmap: Bitmap

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
                val photo = dialogLayout.findViewById<ImageView>(R.id.photo)

                photo.click({
                    uploadImageGallery()
                })
                editName.setText(props.name)
                with(builder) {
                    this?.setPositiveButton("Save") { dialog, which ->
                        name.text = editName.text.toString()
                        props.saveName(editName.text.toString())
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageBitmap =
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver, data?.data!!)).copy(
                    Bitmap.Config.RGBA_F16, true
                )
        }
        props.savePhoto(bitmapToByte())
        binding?.photo?.setImageBitmap(bitmapFromByte(DataBaseRepository.getPhoto()))
        Log.d("======", DataBaseRepository.getPhoto().toString())
    }

    private fun uploadImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/"

        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    fun bitmapToByte(): String {
        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos) //bm is the bitmap object
        val b = baos.toByteArray()
        val encoded = Base64.encodeToString(b, Base64.DEFAULT)
        Log.d("------", encoded.toString())
        return encoded
    }
    fun bitmapFromByte(encoded: String): Bitmap {
        val imageAsBytes: ByteArray = Base64.decode(encoded.getBytes(), Base64.DEFAULT)
        val image = this.findViewById(R.id.ImageView) as ImageView
        image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size))
        Log.d("----", "a.toString()")
        return Base64.encodeToString(byteArray, Base64.DEFAULT) as Bitmap
    }
}
