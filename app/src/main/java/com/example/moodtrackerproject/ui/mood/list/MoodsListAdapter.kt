package com.example.moodtrackerproject.ui.mood.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.databinding.HomeMoodItemBinding
import com.example.moodtrackerproject.ui.mood.list.MoodProps.MoodItemProps

class MoodsListAdapter : ListAdapter<MoodItemProps, MoodsListAdapter.MoodHolder>(MoodItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodHolder {
        return MoodHolder(
            HomeMoodItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoodHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MoodHolder(private val binding: HomeMoodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mood: MoodItemProps) {
            binding.run {
                // emoji.setImageResource(mood.emojiSrc)
                emojiTitle.text = mood.moodTitle
                emojiTime.text = mood.moodTime
            }
        }
    }

    class MoodItemDiffCallback : DiffUtil.ItemCallback<MoodItemProps>() {

        override fun areItemsTheSame(
            oldItem: MoodItemProps,
            newItem: MoodItemProps
        ): Boolean = oldItem.emojiSrc == newItem.emojiSrc

        override fun areContentsTheSame(
            oldItem: MoodItemProps,
            newItem: MoodItemProps
        ): Boolean = oldItem == newItem
    }
}
