package com.example.moodtrackerproject.ui.mood.tests

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.TestOptionsItemBinding
import com.example.moodtrackerproject.ui.mood.tests.StressTestProps.OptionItemProps

class TestListAdapter : ListAdapter<OptionItemProps, TestListAdapter.TestHolder>(OptionItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
        return TestHolder(
            TestOptionsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TestHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TestHolder(private val binding: TestOptionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun bind(props: OptionItemProps) {
            binding.run {
                option.text = props.text
                root.setOnClickListener {
                    props.checkChanged.invoke()
                    Log.d("thee fuuuck ====", props.points.toString())
                }
                card.setBackgroundResource(
                    if (props.isChecked) R.drawable.stress_bg
                    else R.drawable.add_mood_text_bg
                )
            }
        }
    }

    class OptionItemDiffCallback : DiffUtil.ItemCallback<OptionItemProps>() {

        override fun areItemsTheSame(
            oldItem: OptionItemProps,
            newItem: OptionItemProps
        ): Boolean = oldItem.text == newItem.text

        override fun areContentsTheSame(
            oldItem: OptionItemProps,
            newItem: OptionItemProps
        ): Boolean = oldItem == newItem
    }
}
