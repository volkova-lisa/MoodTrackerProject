package com.example.moodtrackerproject.ui.health

import EditHealthProps
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
            var tempWater = 0 // should be props.waterNum
            var tempKcal = 0
            var tempSteps = 0
            var tempSleep = 0.0f

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
                tempSleep -= 0.5f
                if (tempSleep in 0.0f..24.0f) sleep.waterNum.text = tempSleep.toString()
                else tempSleep = 0.0f
            }
            sleep.plusB.quickClick {
                tempSleep += 0.5f
                if (tempSleep in 0.0f..24.0f) sleep.waterNum.text = tempSleep.toString()
                else tempSleep = 0.0f
            }

            cancelButton.click {
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
