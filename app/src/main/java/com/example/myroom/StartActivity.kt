package com.example.myroom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myroom.databinding.ActivityStartBinding
import com.example.myroom.dbRoom.MainDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StartActivity : AppCompatActivity(), RecyclerViewEvent {
    lateinit var binding: ActivityStartBinding
    lateinit var db : MainDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MainDb.getDb(this)
        lifecycleScope.launch {
            var adapter = NoteAdapter(db.getDao().getAllNotes(),this@StartActivity)
            binding.rvNotes.layoutManager = LinearLayoutManager(this@StartActivity)
            binding.rvNotes.adapter = adapter
        }





        binding.btnAddNote.setOnClickListener{
            val myIntent = Intent(this, MainActivity::class.java)
            this.startActivity(myIntent)
        }
    }


    /*override fun onResume() {
        super.onResume()
        MyGlobalScope.coroutineScope.launch {
            var adapter = NoteAdapter(db.getDao().getAllNotes(),this@StartActivity)
            binding.rvNotes.layoutManager = LinearLayoutManager(this@StartActivity)
            binding.rvNotes.adapter = adapter
        }
    }*/

    override fun onItemClick(position: Int) {

        val intent = Intent(this, NoteDetailActivity::class.java)
        lifecycleScope.launch {

            var notes = db.getDao().getAllNotes()
            intent.putExtra("noteID", notes[position].id)
            intent.putExtra("noteTitle", notes[position].title)
            intent.putExtra("noteContent", notes[position].content)
            startActivity(intent)


        }

    }
}