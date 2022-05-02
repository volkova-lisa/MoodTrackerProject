package com.example.moodtrackerproject.ui.mood.tests

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.TestOptionsItemBinding

class TestAdapter(var listOfOptions: List<OptionUiModel> = emptyList()) :
    RecyclerView.Adapter<TestAdapter.TestHolder>() {

    class TestHolder(private val binding: TestOptionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun bind(opt: OptionUiModel) {
            binding.run {
                option.text = opt.text
                root.setOnClickListener {
                    opt.checkChanged?.invoke(opt.text)
                }
                card.setBackgroundResource(
                    if (opt.isChecked) R.drawable.stress_bg
                    else R.drawable.add_mood_text_bg
                )
            }
        }
    }

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
        holder.bind(listOfOptions[position])
    }

    override fun getItemCount(): Int = listOfOptions.size

    fun clearChosen() {
    }

    fun setList(list: List<OptionUiModel>) {
        listOfOptions = list
        notifyDataSetChanged()
    }
}
