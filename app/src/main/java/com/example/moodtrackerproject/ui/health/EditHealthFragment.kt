package com.example.moodtrackerproject.ui.health

import EditHealthProps
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentEditHealthBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.utils.click

class EditHealthFragment : BaseFragment<EditHealthViewModel, FragmentEditHealthBinding, EditHealthProps>(
    EditHealthViewModel::class.java
) {

    private lateinit var props: EditHealthProps

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentEditHealthBinding = FragmentEditHealthBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun render(props: EditHealthProps) {
        binding?.run {
            cancelButton.click {
                (requireActivity() as MainActivity).router.openHealth()
            }
        }
    }
}
