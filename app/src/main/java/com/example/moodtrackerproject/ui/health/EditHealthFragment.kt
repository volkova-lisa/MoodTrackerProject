package com.example.moodtrackerproject.ui.health

import EditHealthProps
import EditHealthProps.*
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentEditHealthBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.utils.click
import com.example.moodtrackerproject.utils.quickClick

class EditHealthFragment :
    BaseFragment<EditHealthViewModel, FragmentEditHealthBinding, EditHealthProps>(
        EditHealthViewModel::class.java
    ),
    SensorEventListener {

    private lateinit var props: EditHealthProps
    var sensorManager: SensorManager? = null
    var running = false
    var totalSteps = 0f
    var previousTotalSteps = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentEditHealthBinding = FragmentEditHealthBinding.inflate(inflater, container, false)

    override fun onResume() {
        super.onResume()
        running = true
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        binding?.steps?.load?.click {
            if (stepSensor == null) {
                Toast.makeText(activity, "No sensor detected on this device", Toast.LENGTH_SHORT)
                    .show()
            } else {
                sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
            }
        }
    }

    override fun render(props: EditHealthProps) {
        binding?.run {
            var tempWater = 0
            var tempKcal = 0
            var tempSteps = 0
            var tempSleep = 0

            steps.waterNum.text = props.listHealth[1].toString()
            water.waterNum.text = props.listHealth[0].toString()
            sleep.waterNum.text = props.listHealth[2].toString()
            kcal.waterNum.text = props.listHealth[3].toString()

            sensorManager = activity?.getSystemService(SENSOR_SERVICE) as SensorManager

            steps.minus.quickClick {
                tempSteps -= 200
                if (tempSteps in 0..90000) steps.waterNum.text = tempSteps.toString()
                else tempSteps = 0
            }
            steps.plus.quickClick {
                tempSteps += 200
                if (tempSteps in 0..90000) steps.waterNum.text = tempSteps.toString()
                else tempSteps = 0
            }

            water.minusButt.quickClick {
                tempWater -= 100
                if (tempWater in 0..4000) water.waterNum.text = tempWater.toString()
                else tempWater = 0
            }
            water.plusButt.quickClick {
                tempWater += 100
                if (tempWater in 0..4000) water.waterNum.text = tempWater.toString()
                else tempWater = 0
            }

            kcal.minusButton.quickClick {
                tempKcal -= 100
                if (tempKcal in 0..4000) kcal.waterNum.text = tempKcal.toString()
                else tempKcal = 0
            }
            kcal.plusButton.quickClick {
                tempKcal += 100
                if (tempKcal in 0..4000) kcal.waterNum.text = tempKcal.toString()
                else tempKcal = 0
            }

            sleep.minusB.quickClick {
                tempSleep -= 1
                if (tempSleep in 0..24) sleep.waterNum.text = tempSleep.toString()
                else tempSleep = 0
            }
            sleep.plusB.quickClick {
                tempSleep += 1
                if (tempSleep in 0..24) sleep.waterNum.text = tempSleep.toString()
                else tempSleep = 0
            }

            cancelButton.click {
                (requireActivity() as MainActivity).router.openHealth()
            }

            saveButton.click {
                props.saveEdited(listOf(tempWater, tempSteps, tempSleep, tempKcal))
            }
            if (props.action == EditHealthScreenActions.StartHealthScreen) {
                (requireActivity() as MainActivity).router.openHealth()
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (running) {
            totalSteps = event!!.values[0]
            val currSteps = totalSteps.toInt() - previousTotalSteps.toInt()
            binding?.steps?.load?.click { binding?.steps?.waterNum?.text = ("$currSteps") }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
}
