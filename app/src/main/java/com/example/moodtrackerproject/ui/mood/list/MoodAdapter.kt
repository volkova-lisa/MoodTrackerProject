package com.example.moodtrackerproject.ui.mood.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.databinding.HomeMoodItemBinding

class MoodAdapter(var listOfMoods: List<MoodBody> = emptyList()) :
    RecyclerView.Adapter<MoodAdapter.MoodHolder>() {

    class MoodHolder(private val binding: HomeMoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(mood: MoodBody) {
                binding.run {
                    //emoji.setImageResource(mood.emojiSrc)
                    emojiTitle.text = mood.moodTitle
                    emojiTime.text = mood.moodTime
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodAdapter.MoodHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MoodHolder, position: Int) {
        holder.bind(listOfMoods[position])
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun setList(list: List<MoodBody>) {
        listOfMoods = list
        notifyDataSetChanged()
    }


}