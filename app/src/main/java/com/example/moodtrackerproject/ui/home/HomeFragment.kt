package com.example.moodtrackerproject.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.data.DataBaseRepository
import com.example.moodtrackerproject.databinding.FragmentHomeBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.home.HomeProps.HomeAction
import com.example.moodtrackerproject.ui.mood.list.MoodsListAdapter
import com.example.moodtrackerproject.utils.convertToBitmap

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, HomeProps>(
    HomeViewModel::class.java
) {

    private val moodsAdapter = MoodsListAdapter()
    private val notesAdapter = HomeNotesAdapter()

    private lateinit var props: HomeProps

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.setSupportActionBar(binding?.toolbar)
        setHasOptionsMenu(true)
        binding?.emojiList?.adapter = moodsAdapter
        binding?.notesList?.adapter = notesAdapter
    }

    override fun onResume() {
        super.onResume()
        if (::props.isInitialized) {
            props.fetchListOfMoods()
            props.fetchListOfNotes()
            props.fetchHealth()
            props.fetchResults()
            props.fetchName()
        }
    }

    override fun render(props: HomeProps) {
        this.props = props
        props.action?.let { handleAction(it) }
        notesAdapter.submitList(props.listOfNotesToday)
        moodsAdapter.submitList(props.listOfMoodsToday)
        binding?.run {
            photo.setImageBitmap((DataBaseRepository.getPhoto()).convertToBitmap(resources))
            name.text = props.name
            if (props.testResults != null) {
                angerJoy.angerBar.progress = props.testResults.stressResult
                sadHappy.angerBar.progress = props.testResults.anxResult
            }

            if (props.healthItems != null) {
                val water = props.healthItems.water
                val steps = props.healthItems.steps
                val sleep = props.healthItems.sleep
                val kcal = props.healthItems.kcal

                waterItem.waterValue.text = "$water ml"
                stepsItem.stepsValue.text = steps.toString()
                sleepItem.sleepValue.text = "$sleep h"
                kcalItem.waterValue.text = kcal.toString()
                waterItem.waterBar.max =
                    props.healthMax.waterMax
                waterItem.waterBar.progress = water

                stepsItem.stepsBar.max =
                    props.healthMax.stepsMax
                stepsItem.stepsBar.progress = steps

                sleepItem.waterBar.max = props.healthMax.sleepMax
                sleepItem.waterBar.progress = if (sleep > 8) 8 else sleep

                kcalItem.waterBar.max = props.healthMax.kcalMax
                kcalItem.waterBar.progress = kcal
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logOut -> props.logout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleAction(homeAction: HomeAction) {
        when (homeAction) {
            is HomeAction.LogOut -> (requireActivity() as MainActivity).router.openWelcome()
        }
    }
}
