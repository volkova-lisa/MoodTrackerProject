package com.example.moodtrackerproject.ui.settings

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.example.moodtrackerproject.MainActivity
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

    override fun render(props: SettingsProps) {
        this.props = props
        binding?.run {
            if (props.language == getString(R.string.en)) {
                langSwitch.isChecked = true
                enTitle.setTextColor(resources.getColor(R.color.light_purple))
            } else uaTitle.setTextColor(resources.getColor(R.color.light_purple))
            darkModeSwitch.isChecked = props.isDarkOn
            imageBitmap = props.photo.convertToBitmap(resources)
            photo.setImageBitmap(imageBitmap)
            name.text = props.name
            emailSett.text = props.email
            langSwitch.setOnCheckedChangeListener { buttonView, onSwitch ->
                if (onSwitch) {
                    Lingver.getInstance().setLocale(requireContext(), getString(R.string.en))
                    props.saveLang(Lingver.getInstance().getLanguage())
                    enTitle.setTextColor(resources.getColor(R.color.light_purple))
                    uaTitle.setTextColor(resources.getColor(R.color.text_grey))
                } else {
                    Lingver.getInstance().setLocale(requireContext(), getString(R.string.ua))
                    props.saveLang(Lingver.getInstance().getLanguage())
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
                photoAlertDialog.click({
                    uploadImageGallery()
                })
                editName.setText(props.name)
                with(builder) {
                    this?.setPositiveButton(getString(R.string.save)) { dialog, which ->
                        name.text = editName.text.toString()
                        imageBitmap = onActivityResultImageBitmap
                        props.saveName(editName.text.toString())
                        props.savePhoto(onActivityResultImageBitmap.convertToString())
                        props.fetchSettings()
                    }
                    this?.setNegativeButton(getString(R.string.cancel)) { dialog, which ->
                        toast(getString(R.string.not_saved))
                    }
                    this?.setView(dialogLayout)
                    this?.show()
                }
            })

            paramTitle.click({
                val builder = context?.let { AlertDialog.Builder(it) }
                val dialogLayout = layoutInflater.inflate(R.layout.edit_health_par_dialog, null)
                val editWater = dialogLayout.findViewById<EditText>(R.id.water_edit)
                val editSteps = dialogLayout.findViewById<EditText>(R.id.steps_edit)
                val editSleep = dialogLayout.findViewById<EditText>(R.id.sleep_edit)
                val editKcal = dialogLayout.findViewById<EditText>(R.id.kcal_edit)

                with(builder) {
                    this?.setPositiveButton(getString(R.string.save)) { dialog, which ->
                        props.saveHealthMax(
                            editWater.text.toString().toInt(),
                            editSteps.text.toString().toInt(),
                            editSleep.text.toString().toInt(),
                            editKcal.text.toString().toInt()
                        )
                    }
                    this?.setNegativeButton(getString(R.string.cancel)) { dialog, which ->
                        toast(getString(R.string.not_saved))
                    }
                    this?.setView(dialogLayout)
                    this?.show()
                }
            })

            logoutTitle.click({
                props.logout()
            })
            passTitle.click({
                val builder = context?.let { AlertDialog.Builder(it) }
                val dialogLayout = layoutInflater.inflate(R.layout.edit_password, null)
                val currPass = dialogLayout.findViewById<EditText>(R.id.curr_pass)
                val newPass = dialogLayout.findViewById<EditText>(R.id.new_pass)
                val confPass = dialogLayout.findViewById<EditText>(R.id.conf_pass)

                with(builder) {
                    this?.setPositiveButton(getString(R.string.save)) { dialog, which ->
                        props.changePassword(
                            currPass.text.toString(),
                            newPass.text.toString(),
                            confPass.text.toString()
                        )
                    }
                    this?.setNegativeButton(getString(R.string.cancel)) { dialog, which ->
                        toast(getString(R.string.not_saved))
                    }
                    this?.setView(dialogLayout)
                    this?.show()
                }
            })
            if (props.action == SettingsProps.SettingsActions.LogOut) {
                (requireActivity() as MainActivity).router.openWelcome()
            }
        }
    }

    private fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    private fun uploadImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                onActivityResultImageBitmap = ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        requireContext().contentResolver,
                        data?.data!!
                    )
                ).copy(
                    Bitmap.Config.RGBA_F16, true
                )
            photoAlertDialog.setImageBitmap(onActivityResultImageBitmap)
        } else toast(getString(R.string.not_saved))
    }
}
