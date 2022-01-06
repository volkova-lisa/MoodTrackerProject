package com.example.moodtrackerproject.ui.notes

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moodtrackerproject.R
import com.example.moodtrackerproject.data.NoteBody
import kotlinx.android.synthetic.main.note_item.view.*

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    private var listOfNotes = emptyList<NoteBody>()

    class NotesHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noteDate: TextView = view.note_text_date_time
        val noteTitle: TextView = view.note_title_item
        val noteText: TextView = view.note_text_item
    }

    override fun onViewAttachedToWindow(holder: NotesHolder) {
        holder.itemView.setOnClickListener {
            NotesFragment.click(listOfNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: NotesHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NotesHolder(view)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        holder.noteDate.text = listOfNotes[position].date
        holder.noteTitle.text = listOfNotes[position].title
        holder.noteText.text = listOfNotes[position].text
    }

    override fun getItemCount(): Int = listOfNotes.size

    fun setList(list: MutableList<NoteBody>) {
        listOfNotes = list
        notifyDataSetChanged()
        Log.d("ooooooooooooooooooooooooo", listOfNotes.toString())
    }
}
