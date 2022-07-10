package com.example.moodtrackerproject.ui.health

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentHealthBinding
import com.example.moodtrackerproject.ui.BaseFragment

class HealthFragment : BaseFragment<HealthViewModel, FragmentHealthBinding, HealthProps>(
    HealthViewModel::class.java
) {

    private lateinit var props: HealthProps

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentHealthBinding = FragmentHealthBinding.inflate(inflater, container, false)

    override fun onResume() {
        super.onResume()
        if (::props.isInitialized) {
        }
    }

    override fun render(props: HealthProps) {
        Log.d("RENDER", "8888")
        this.props = props
        binding?.run {
            editHButton.setOnClickListener() {
                (requireActivity() as MainActivity).router.openEditHealth()
            }
        }
    }
}
