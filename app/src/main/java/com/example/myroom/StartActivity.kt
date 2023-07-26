package com.example.myroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myroom.databinding.ActivityStartBinding
import com.example.myroom.dbRoom.MainDb

class StartActivity : AppCompatActivity(), RecyclerViewEvent {
    lateinit var binding: ActivityStartBinding
    lateinit var db : MainDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MainDb.getDb(this)
        db.getDao().getAllNotes().asLiveData().observe(this){
            var adapter = NoteAdapter(it, this)
            binding.rvNotes.layoutManager = LinearLayoutManager(this)
            binding.rvNotes.adapter = adapter
        }


        binding.btnAddNote.setOnClickListener{
            val myIntent = Intent(this, MainActivity::class.java)
            this.startActivity(myIntent)
        }
    }


    override fun onResume() {
        super.onResume()
        db.getDao().getAllNotes().asLiveData().observe(this){
            var adapter = NoteAdapter(it, this)
            binding.rvNotes.layoutManager = LinearLayoutManager(this)
            binding.rvNotes.adapter = adapter
        }
    }

    override fun onItemClick(position: Int) {

        val intent = Intent(this, NoteDetailActivity::class.java)
        db.getDao().getAllNotes().asLiveData().observe(this){
            intent.putExtra("noteID", it[position].id)
            intent.putExtra("noteTitle", it[position].title)
            intent.putExtra("noteContent", it[position].content)
            startActivity(intent)
        }

    }
}