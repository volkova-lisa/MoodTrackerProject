package com.example.moodtrackerproject.ui.mood.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.databinding.HomeNoteItemBinding
import com.example.moodtrackerproject.domain.NoteBody

class HomeNotesAdapter(val listOfNotes: MutableList<NoteBody> = mutableListOf()) :
    RecyclerView.Adapter<HomeNotesAdapter.HomeNotesHolder>() {

    class HomeNotesHolder(private val binding: HomeNoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteBody) {
            binding.run {
                Log.d("IMAGESRC", note.title)
                // getImageFromCloud(mood.emojiSrc)
                // emoji.setImageBitmap(mood.emojiSrc.getImageFromCloud())
                titleOfNote.text = note.title
                textOfNote.text = note.text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNotesAdapter.HomeNotesHolder {
        return HomeNotesHolder(
            HomeNoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeNotesHolder, position: Int) {
        holder.bind(listOfNotes[position])
    }

    override fun getItemCount(): Int = listOfNotes.size

    fun setList(list: List<NoteBody>) {
        listOfNotes.clear()
        listOfNotes.addAll(list)
        notifyDataSetChanged()
    }
}
