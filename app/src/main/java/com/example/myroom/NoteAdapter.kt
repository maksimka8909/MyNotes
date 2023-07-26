package com.example.myroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myroom.dbRoom.Note

class NoteAdapter(private val notes: List<Note>,
                  private val listener: RecyclerViewEvent
                  ) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val cardView : CardView = itemView.findViewById(R.id.cardViewItem)
        val title : TextView = itemView.findViewById(R.id.tvTitle)
        val content : TextView = itemView.findViewById(R.id.tvContent)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item_recycler_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardView.tag = notes[position].id
        holder.title.text = notes[position].title
        if(notes[position].content.length >= 20)
            holder.content.text = notes[position].content.substring(0,19) + "..."
        else holder.content.text = notes[position].content

    }
}