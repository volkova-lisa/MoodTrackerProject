package com.example.moodtrackerproject.ui.health

import EditHealthProps
import EditHealthProps.*
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.FragmentEditHealthBinding
import com.example.moodtrackerproject.domain.HealthModel
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.utils.click

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

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentEditHealthBinding = FragmentEditHealthBinding.inflate(inflater, container, false)

    override fun onResume() {
        super.onResume()
        running = true
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        binding?.steps?.load?.click({
            if (stepSensor == null) {
                Toast.makeText(activity, getString(R.string.no_sensor), Toast.LENGTH_SHORT)
                    .show()
            } else {
                sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
            }
        })
    }

    override fun render(props: EditHealthProps) {
        binding?.run {
            var tempWater = 0
            var tempKcal = 0
            var tempSteps = 0
            var tempSleep = 0

            if (props.listHealth != null) {
                steps.waterNum.text = props.listHealth.steps.toString()
                water.waterNum.text = props.listHealth.water.toString()
                sleep.waterNum.text = props.listHealth.sleep.toString()
                kcal.waterNum.text = props.listHealth.kcal.toString()

                sensorManager = activity?.getSystemService(SENSOR_SERVICE) as SensorManager

                steps.minus.click({
                    tempSteps -= 200
                    if (tempSteps in 0..90000) steps.waterNum.text = tempSteps.toString()
                    else tempSteps = 0
                }, 100)
                steps.plus.click({
                    tempSteps += 200
                    if (tempSteps in 0..90000) steps.waterNum.text = tempSteps.toString()
                    else tempSteps = 0
                }, 100)

                water.minusButt.click({
                    tempWater -= 100
                    if (tempWater in 0..4000) water.waterNum.text = tempWater.toString()
                    else tempWater = 0
                }, 100)
                water.plusButt.click({
                    tempWater += 100
                    if (tempWater in 0..4000) water.waterNum.text = tempWater.toString()
                    else tempWater = 0
                }, 100)

                kcal.minusButton.click({
                    tempKcal -= 100
                    if (tempKcal in 0..4000) kcal.waterNum.text = tempKcal.toString()
                    else tempKcal = 0
                }, 100)
                kcal.plusButton.click({
                    tempKcal += 100
                    if (tempKcal in 0..4000) kcal.waterNum.text = tempKcal.toString()
                    else tempKcal = 0
                }, 100)

                sleep.minusB.click({
                    tempSleep -= 1
                    if (tempSleep in 0..24) sleep.waterNum.text = tempSleep.toString()
                    else tempSleep = 0
                }, 100)
                sleep.plusB.click({
                    tempSleep += 1
                    if (tempSleep in 0..24) sleep.waterNum.text = tempSleep.toString()
                    else tempSleep = 0
                }, 100)

                cancelButton.click({
                    props.startHealth()
                })

                tempWater = water.waterNum.text.toString().toInt()
                tempKcal = kcal.waterNum.text.toString().toInt()
                tempSteps = steps.waterNum.text.toString().toInt()
                tempSleep = sleep.waterNum.text.toString().toInt()
            }
            saveButton.click({
                props.saveEdited(HealthModel(tempWater, tempSteps, tempSleep, tempKcal))
            })
            if (props.action == EditHealthScreenActions.StartHealthScreen) {
                (requireActivity() as MainActivity).router.openHealth()
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (!requireActivity().isFinishing) {
            totalSteps = if (event != null) event.values[0] else 0.0f
            val currSteps = totalSteps.toInt() - previousTotalSteps.toInt()
            binding?.steps?.load?.click({ binding?.steps?.waterNum?.text = ("$currSteps") })
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
}
