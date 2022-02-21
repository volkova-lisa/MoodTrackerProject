package com.example.moodtrackerproject.ui.mood.add

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.EmojiItemBinding

class AddMoodAdapter(var listOfModels: List<EmojiBodyUIModel> = emptyList()) :
    RecyclerView.Adapter<AddMoodAdapter.AddMoodHolder>() {

    class AddMoodHolder(private val binding: EmojiItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(emojiUIModel: EmojiBodyUIModel) {
            binding.run {
                emojiImage.setImageResource(emojiUIModel.image)
                root.setOnClickListener {
                    // emojiUIModel.emojiChecked.invoke(emojiUIModel)
                    emojiUIModel.checkChanged.invoke(emojiUIModel)
                    Log.d("-----ADAPTER", "CLICKED")
                }
                emojiImage.setBackgroundResource(
                    if (emojiUIModel.isChecked) R.drawable.bg_color
                    else R.drawable.emoji_unchecked_bg
                )
            }
        }
    }

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
        holder.bind(listOfModels[position])
    }

    override fun getItemCount(): Int = listOfModels.size

    fun setList(list: List<EmojiBodyUIModel>) {
        listOfModels = list
        notifyDataSetChanged()
    }
}