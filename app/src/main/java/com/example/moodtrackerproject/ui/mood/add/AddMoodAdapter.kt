package com.example.moodtrackerproject.ui.mood.add

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.databinding.EmojiItemBinding

class AddMoodAdapter(var listOfEmojiUIModel: List<EmojiBodyUIModel> = emptyList()) :
    RecyclerView.Adapter<AddMoodAdapter.AddMoodHolder>() {

    class AddMoodHolder(private val binding: EmojiItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(emojiUIModel: EmojiBodyUIModel) {
            binding.run {
                emojiImage.setImageResource(emojiUIModel.image)

                root.setOnClickListener {
                    emojiUIModel.emojiChecked.invoke(emojiUIModel)
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
        holder.bind(listOfEmojiUIModel[position])
        Log.d("++++++++", listOfEmojiUIModel[position].toString())
    }

    override fun getItemCount(): Int = listOfEmojiUIModel.size

    fun setList(list: List<EmojiBodyUIModel>) {
        listOfEmojiUIModel = list
        notifyDataSetChanged()
        Log.d("----========", listOfEmojiUIModel.toString())
    }
}
