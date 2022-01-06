package com.example.moodtrackerproject.ui.notes.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.domain.NoteBody
import com.example.moodtrackerproject.databinding.NoteItemBinding
import kotlinx.android.synthetic.main.note_item.view.*

class NotesAdapter(var listOfNotes: List<NoteBody> = emptyList()) : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

//    private var isFavourite = false

    class NotesHolder(private val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: NoteBodyUiModel) {
            binding.run {
                root.setOnClickListener {
                    model.openDetails(model.noteId)
//                    NotesFragment.click(listOfNotes[holder.adapterPosition])
                }
                noteTextDateTime.text = model.date
                noteTitleItem.text = model.title
                noteText.text = model.text
                noteStarButtonUnchecked.setImageResource(
                    if (model.isChecked) R.drawable.ic_note_star_checked
                    else R.drawable.ic_note_star_unchecked
                )
                noteStarButtonUnchecked.setOnClickListener {
                    model.checkChanged(noteId)
                }
            }
        }
    }

//    override fun onViewAttachedToWindow(holder: NotesHolder) {
//        holder.itemView.setOnClickListener {
//            NotesFragment.click(listOfNotes[holder.adapterPosition])
//        }
//        holder.itemView.note_star_button_unchecked.setOnClickListener {
//            isFavourite = !isFavourite
//            if (isFavourite) {
//                holder.itemView.note_star_button_unchecked.setImageResource(R.drawable.ic_note_star_checked)
//            }
//            if (!isFavourite) {
//                holder.itemView.note_star_button_unchecked.setImageResource(R.drawable.ic_note_star_unchecked)
//            }
//        }
//    }
//
//    override fun onViewDetachedFromWindow(holder: NotesHolder) {
//        holder.itemView.setOnClickListener(null)
//        super.onViewDetachedFromWindow(holder)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        return NotesHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(this),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.bind(listOfNotes[position])
    }

    override fun getItemCount(): Int = listOfNotes.size

    fun setList(list: List<NoteBody>) {
        listOfNotes = list
        notifyDataSetChanged()
        Log.d("ooooooooooooooooooooooooo", listOfNotes.toString())
    }
}
