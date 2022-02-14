package com.example.moodtrackerproject.ui.notes.list

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
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
                    model.openDetails?.invoke(model)
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

                val popupMenu = PopupMenu(root.context, moreButton, Gravity.RIGHT)
                popupMenu.inflate(R.menu.note_item_menu)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.delete -> {
                            model.deleteNote?.invoke(model.noteId)
                            true
                        }
                        else -> true
                    }
                }
                moreButton.setOnClickListener {
                    popupMenu.show()
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
