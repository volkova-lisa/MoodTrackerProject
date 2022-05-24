package com.example.moodtrackerproject.ui.notes.list

import android.view.Gravity
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.databinding.NoteItemBinding
import com.example.moodtrackerproject.ui.notes.list.NotesListProps.NoteItemProps
import com.example.moodtrackerproject.utils.click
import com.example.moodtrackerproject.utils.inflater

class NotesListAdapter : ListAdapter<NoteItemProps, NotesListAdapter.NoteItemViewHolder>(NoteItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        return NoteItemViewHolder(NoteItemBinding.inflate(parent.context.inflater, parent, false))
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NoteItemViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(props: NoteItemProps) {
            binding.run {
                root.click {
                    props.openDetails?.invoke(props)
                }
                noteTextDateTime.text = props.date
                noteTitleItem.text = props.title
                noteTextItem.text = props.text
                noteStarButtonUnchecked.setImageResource(
                    if (props.isChecked) R.drawable.ic_note_star_checked
                    else R.drawable.ic_note_star_unchecked
                )
                noteStarButtonUnchecked.setOnClickListener {
                    props.checkChanged?.invoke(props.noteId)
                }

                val popupMenu = PopupMenu(root.context, moreButton, Gravity.RIGHT)
                popupMenu.inflate(R.menu.note_item_menu)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.delete -> {
                            props.deleteNote?.invoke(props.noteId)
                            true
                        }
                        else -> true
                    }
                }
                moreButton.click {
                    popupMenu.show()
                }
            }
        }
    }

    class NoteItemDiffCallback : DiffUtil.ItemCallback<NoteItemProps>() {

        override fun areItemsTheSame(
            oldItem: NoteItemProps,
            newItem: NoteItemProps
        ): Boolean = oldItem.noteId == newItem.noteId

        override fun areContentsTheSame(
            oldItem: NoteItemProps,
            newItem: NoteItemProps
        ): Boolean = oldItem == newItem
    }
}
