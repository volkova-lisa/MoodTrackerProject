package com.example.moodtrackerproject.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.databinding.HomeNoteItemBinding
import com.example.moodtrackerproject.ui.notes.list.NotesListProps

class HomeNotesAdapter : ListAdapter<NotesListProps.NoteItemProps, HomeNotesAdapter.HomeNotesHolder>(
    HomeNoteDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNotesHolder {
        return HomeNotesHolder(
            HomeNoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeNotesHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeNotesHolder(private val binding: HomeNoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NotesListProps.NoteItemProps) {
            binding.run {
                titleOfNote.text = note.title
                textOfNote.text = note.text
                timeOfNote.text = note.date
            }
        }
    }

    class HomeNoteDiffCallback : DiffUtil.ItemCallback<NotesListProps.NoteItemProps>() {
        override fun areItemsTheSame(
            oldItem: NotesListProps.NoteItemProps,
            newItem: NotesListProps.NoteItemProps
        ): Boolean = oldItem.title == newItem.title

        override fun areContentsTheSame(
            oldItem: NotesListProps.NoteItemProps,
            newItem: NotesListProps.NoteItemProps
        ): Boolean = oldItem == newItem
    }
}
