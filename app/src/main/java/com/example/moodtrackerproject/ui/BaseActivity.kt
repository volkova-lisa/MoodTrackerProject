package com.example.moodtrackerproject.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.utils.hideKeyboard

abstract class BaseActivity<BINDING : ViewBinding> : AppCompatActivity() {

    protected var binding: BINDING? = null
    abstract fun getActivityBinding(inflater: LayoutInflater): BINDING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.light_purple)
        window.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        binding = getActivityBinding(layoutInflater)
        setContentView(binding?.root)
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard(currentFocus)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
