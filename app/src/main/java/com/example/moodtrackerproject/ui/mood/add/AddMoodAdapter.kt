package com.example.moodtrackerproject.ui.mood.add

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.databinding.EmojiItemBinding

class AddMoodAdapter(var listOfEmoji: List<Int> = emptyList()) :
    RecyclerView.Adapter<AddMoodAdapter.AddMoodHolder>() {

    class AddMoodHolder(private val binding: EmojiItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.run {
                emojiImage.setImageResource(image)
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

    fun setList(list: List<Int>) {
        listOfEmoji = list
        notifyDataSetChanged()
        Log.d("----========", listOfEmoji.toString())
    }
}
