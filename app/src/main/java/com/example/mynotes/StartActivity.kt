package com.example.mynotes


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.databinding.ActivityStartBinding
import com.example.mynotes.db.DBManager
import com.example.mynotes.db.RecyclerViewEvent


class StartActivity : AppCompatActivity(), RecyclerViewEvent {
    lateinit var binding: ActivityStartBinding
    val dbManager = DBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        dbManager.openDb()
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val dataList = dbManager.readDBData()
        var adapter = NoteAdapter(dataList, this)
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = adapter
        binding.btnAddNote.setOnClickListener{
            val myIntent = Intent(this, MainActivity::class.java)
            this.startActivity(myIntent)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }

    override fun onResume() {
        super.onResume()
        val dataList = dbManager.readDBData()
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = NoteAdapter(dataList, this)

    }

    override fun onItemClick(position: Int) {

        val dataList = dbManager.readDBData()
        val note = dataList[position]
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra("noteID", note.id)
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteContent", note.content)
        startActivity(intent)
    }


}