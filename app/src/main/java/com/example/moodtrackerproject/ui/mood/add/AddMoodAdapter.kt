package com.example.moodtrackerproject.ui.mood.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.EmojiItemBinding
import com.example.moodtrackerproject.ui.mood.add.AddMoodProps.MoodItemProps
import com.example.moodtrackerproject.utils.click

class AddMoodAdapter : ListAdapter<MoodItemProps, AddMoodAdapter.AddMoodHolder>(AddMoodDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddMoodHolder {
        return AddMoodHolder(
            EmojiItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AddMoodHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AddMoodHolder(private val binding: EmojiItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(props: MoodItemProps) {
            binding.run {
                emojiImage.setImageResource(props.image)
                emojiImage.setBackgroundResource(
                    if (props.isChecked) R.drawable.emoji_checked_bg
                    else R.drawable.emoji_unchecked_bg
                )
                root.click(props.checkChanged)
            }
        }
    }

    class AddMoodDiffCallback : DiffUtil.ItemCallback<MoodItemProps>() {

        override fun areItemsTheSame(
            oldItem: MoodItemProps,
            newItem: MoodItemProps
        ): Boolean = oldItem.image == newItem.image

        override fun areContentsTheSame(
            oldItem: MoodItemProps,
            newItem: MoodItemProps
        ): Boolean = oldItem == newItem
    }
}
