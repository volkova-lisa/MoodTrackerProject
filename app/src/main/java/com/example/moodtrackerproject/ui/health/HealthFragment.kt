package com.example.moodtrackerproject.ui.health

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moodtrackerproject.databinding.FragmentHealthBinding
import com.example.moodtrackerproject.ui.BaseFragment

class HealthFragment : BaseFragment<HealthViewModel, FragmentHealthBinding, HealthProps>(
    HealthViewModel::class.java
) {

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentHealthBinding = FragmentHealthBinding.inflate(inflater, container, false)

    override fun render(props: HealthProps) {
        binding?.run {
        }
    }
}
