package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mynotes.databinding.ActivityNoteDetailBinding
import com.example.mynotes.databinding.ActivityStartBinding
import com.example.mynotes.db.DBManager

class NoteDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityNoteDetailBinding
    val dbManager = DBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        dbManager.openDb()
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.etTitle.setText(intent.getStringExtra("noteTitle"))
        binding.etContent.setText(intent.getStringExtra("noteContent"))
        binding.btnDeleteNote.setOnClickListener{
            dbManager.deleteFromDb(intent.getIntExtra("noteID", 0))
            val myIntent = Intent(this, StartActivity::class.java)
            this.startActivity(myIntent)
        }
        binding.btnUpgradeNote.setOnClickListener {
            if((binding.etTitle.text.toString().trim() != null)
                &&(binding.etContent.text.toString().trim() != null)){
                dbManager.updateFromDb(intent.getIntExtra("noteID", 0),
                    binding.etTitle.text.toString(),
                    binding.etContent.text.toString())
                val myIntent = Intent(this, StartActivity::class.java)
                this.startActivity(myIntent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }
}