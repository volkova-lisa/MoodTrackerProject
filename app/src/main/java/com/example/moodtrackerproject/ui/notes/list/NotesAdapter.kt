package com.example.moodtrackerproject.ui.notes.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.NoteItemBinding

class NotesAdapter(var listOfNotes: List<NoteBodyUiModel> = emptyList()) :
    RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    // TODO("make with diffutils")

    class NotesHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: NoteBodyUiModel) {
            binding.run {
                root.setOnClickListener {
                    model.openDetails?.invoke(model.noteId)
                    Log.d("-------------------", "111111111")
                }
                noteTextDateTime.text = model.date
                noteTitleItem.text = model.title
                noteTextItem.text = model.text
                noteStarButtonUnchecked.setImageResource(
                    if (model.isChecked) R.drawable.ic_note_star_checked
                    else R.drawable.ic_note_star_unchecked
                )
                noteStarButtonUnchecked.setOnClickListener {
                    model.checkChanged?.invoke(model.noteId)
                }
                buttonDeleteItem.setOnClickListener {
                    model.deleteNote?.invoke(model.noteId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        return NotesHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.bind(listOfNotes[position])
    }

    override fun getItemCount(): Int = listOfNotes.size

    fun setList(list: List<NoteBodyUiModel>) {
        listOfNotes = list
        notifyDataSetChanged()
    }
}
