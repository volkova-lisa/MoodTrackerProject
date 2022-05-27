package com.example.moodtrackerproject.ui.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moodtrackerproject.databinding.FragmentStatisticsBinding
import com.example.moodtrackerproject.ui.BaseFragment

class StatisticsFragment : BaseFragment<StatisticsViewModel, FragmentStatisticsBinding, StatisticsProps>(
    StatisticsViewModel::class.java
) {

    override fun getFragmentBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentStatisticsBinding = FragmentStatisticsBinding.inflate(inflater, container, false)

    override fun render(props: StatisticsProps) {
        binding?.run {
        }
    }
}
