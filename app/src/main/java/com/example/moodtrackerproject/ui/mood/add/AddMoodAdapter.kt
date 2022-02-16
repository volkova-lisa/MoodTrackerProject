package com.example.moodtrackerproject.ui.mood.add

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.databinding.EmojiItemBinding

class AddMoodAdapter(var listOfEmoji: List<EmojiBody> = emptyList()) :
    RecyclerView.Adapter<AddMoodAdapter.AddMoodHolder>() {

    class AddMoodHolder(private val binding: EmojiItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(emoji: EmojiBody) {
            binding.run {
                emojiImage.setImageResource(emoji.image)

                root.setOnClickListener {
                }
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
        holder.bind(listOfEmoji[position])
        Log.d("++++++++", listOfEmoji[position].toString())
    }

    override fun getItemCount(): Int = listOfEmoji.size

    fun setList(list: List<EmojiBody>) {
        listOfEmoji = list
        notifyDataSetChanged()
        Log.d("----========", listOfEmoji.toString())
    }
}
