package com.example.moodtrackerproject.ui.welcome

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentWelcomeScreenBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.utils.click

class WelcomeFragment : BaseFragment<WelcomeViewModel, FragmentWelcomeScreenBinding, WelcomeProps>(
    WelcomeViewModel::class.java
) {

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentWelcomeScreenBinding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)

    override fun render(props: WelcomeProps) {
        binding?.run {
            welcomeButton.click(props.openHome)
            if (props.action == WelcomeAction.StartHomesScreen) {
                (requireActivity() as MainActivity).router.openLoginFromWelcome()
            }
        }
    }
}
