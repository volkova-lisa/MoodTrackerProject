package com.example.moodtrackerproject.ui.health

import android.util.Log
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
        }
    }

    override fun render(props: HealthProps) {
        this.props = props
        binding?.run {

            water.waterNum.text = props.water.toString()
            sleep.waterNum.text = props.sleep.toString()
            kcal.waterNum.text = props.kcal.toString()
            steps.waterNum.text = props.steps.toString()

            editHButton.click {
                props.startEdit()
                Log.d("---------", props.action.toString())
            }

            Log.d("222222222", props.action.toString())

            if (props.action == HealthScreenActions.StartEditHealthScreen) {
                Log.d("++++++++", props.action.toString())
                (requireActivity() as MainActivity).router.openEditHealth()
            }
        }
    }
}
