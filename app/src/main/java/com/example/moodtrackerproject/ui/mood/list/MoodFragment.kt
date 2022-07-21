package com.example.moodtrackerproject.ui.mood.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moodtrackerproject.MainActivity
import com.example.moodtrackerproject.databinding.FragmentMoodBinding
import com.example.moodtrackerproject.ui.BaseFragment
import com.example.moodtrackerproject.ui.mood.list.MoodProps.MoodScreenActions
import com.example.moodtrackerproject.utils.click

class MoodFragment : BaseFragment<MoodViewModel, FragmentMoodBinding, MoodProps>(
    MoodViewModel::class.java
) {

    private lateinit var props: MoodProps

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentMoodBinding = FragmentMoodBinding.inflate(inflater, container, false)

    private val moodsAdapter = MoodsListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.emojiList?.adapter = moodsAdapter
    }

    override fun onResume() {
        super.onResume()
        if (::props.isInitialized) {
            props.fetchListOfMoods()
        }
    }

    override fun render(props: MoodProps) {
        this.props = props
        binding?.run {
            moodsAdapter.submitList(props.listOfMoods)
            moodInclude.addMoodBtn.click(props.addNewMood)
            stressInclude.root.click({
                props.stressTestConditions()
                Log.d("00000000", props.testType.toString())
            })

            anxietyInclude.root.click({
                props.anxietyTestConditions()
                Log.d("9999999", props.testType.toString())
            })
            if (props.action == MoodScreenActions.StartStressTestScreen || props.action == MoodScreenActions.AnxStressTestScreen) {
                (requireActivity() as MainActivity).router.openStressTest()
            }

            if (props.action == MoodScreenActions.StartAddMoodScreen) {
                (requireActivity() as MainActivity).router.openAddMood()
            }
        }
    }
}
