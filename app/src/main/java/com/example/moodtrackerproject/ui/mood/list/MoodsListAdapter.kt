package com.example.moodtrackerproject.ui.mood.list

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.HomeMoodItemBinding
import com.example.moodtrackerproject.ui.mood.list.MoodProps.MoodItemProps
import com.example.moodtrackerproject.utils.click

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
                emoji.setImageResource(mood.emojiSrc)
                emojiTitle.text = mood.moodTitle
                emojiTime.text = mood.moodTime

                val popupMenu = PopupMenu(root.context, moreButton, Gravity.RIGHT)
                popupMenu.inflate(R.menu.note_item_menu)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.delete -> {
                            mood.deleteMood?.invoke(mood.moodId)
                            true
                        }
                        else -> true
                    }
                }
                moreButton.click({
                    popupMenu.show()
                })
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
