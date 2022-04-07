package com.example.moodtrackerproject.ui.mood.tests

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.databinding.TestOptionsItemBinding

class TestAdapter(var listOfOptions: List<String> = emptyList()) :
    RecyclerView.Adapter<TestAdapter.TestHolder>() {

    class TestHolder(private val binding: TestOptionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(option: String) {
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

    fun setList(list: List<String>) {
        listOfOptions = list
        notifyDataSetChanged()
    }
}
