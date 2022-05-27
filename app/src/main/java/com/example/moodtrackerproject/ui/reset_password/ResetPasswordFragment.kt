package com.example.moodtrackerproject.ui.reset_password

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moodtrackerproject.databinding.FragmentResetPasswordBinding
import com.example.moodtrackerproject.ui.BaseFragment

class ResetPasswordFragment : BaseFragment<ResetPasswordViewModel, FragmentResetPasswordBinding, ResetPasswordProps>(
    ResetPasswordViewModel::class.java
) {

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentResetPasswordBinding = FragmentResetPasswordBinding.inflate(inflater, container, false)

    override fun render(props: ResetPasswordProps) {
        binding?.run {
        }
    }
}
