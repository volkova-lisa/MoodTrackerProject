package com.example.moodtrackerproject.ui.health

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
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

            if (props.healthItems != null) {
                val waterMax = props.healthMax.waterMax
                val stepsMax = props.healthMax.stepsMax
                water.waterNum.text = props.healthItems.water.toString()
                water.explanation.text = getString(R.string.out_of_ml, waterMax)
                steps.waterNum.text = props.healthItems.steps.toString()
                steps.explanation.text = getString(R.string.out_of_h, stepsMax)
                sleep.waterNum.text = props.healthItems.sleep.toString()
                kcal.waterNum.text = props.healthItems.kcal.toString()
            }
            editHButton.click({ props.startEdit() })

            if (props.action == HealthScreenActions.StartEditHealthScreen) {
                (requireActivity() as MainActivity).router.openEditHealth()
            }
        }
    }
}
