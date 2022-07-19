package com.example.moodtrackerproject.ui.health

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentHealthBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.health.HealthProps.HealthScreenActions
import com.example.moodtrackerproject.utils.click

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
            props.fetchListOfHealth()
        }
    }

    override fun render(props: HealthProps) {
        this.props = props
        binding?.run {

            water.waterNum.text = props.currWater.toString()
            steps.waterNum.text = props.currSteps.toString()
            sleep.waterNum.text = props.currSleep.toString()
            kcal.waterNum.text = props.currKcal.toString()

            editHButton.click {
                props.startEdit()
            }

            if (props.action == HealthScreenActions.StartEditHealthScreen) {
                (requireActivity() as MainActivity).router.openEditHealth()
            }
        }
    }
}
