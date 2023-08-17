package com.example.myroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.myroom.databinding.ActivityNoteDetailBinding
import com.example.myroom.dbRoom.MainDb
import com.example.myroom.dbRoom.Note
import kotlinx.coroutines.launch

class NoteDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityNoteDetailBinding
    lateinit var db: MainDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MainDb.getDb(this)
        binding.etTitle.setText(intent.getStringExtra("noteTitle"))
        binding.etContent.setText(intent.getStringExtra("noteContent"))
        binding.btnDeleteNote.setOnClickListener{

            lifecycleScope.launch{
                db.getDao().deleteNote(
                    Note(intent.getIntExtra("noteID", 0),
                        intent.getStringExtra("noteTitle").orEmpty(),
                        intent.getStringExtra("noteContent").orEmpty()
                    ))
            }
            val myIntent = Intent(this, StartActivity::class.java)
            this.startActivity(myIntent)
        }
        binding.btnUpgradeNote.setOnClickListener {
            if((binding.etTitle.text.toString().trim() != "")
                &&(binding.etContent.text.toString().trim() != "")){
                lifecycleScope.launch{
                    db.getDao().updateNote(
                        Note(intent.getIntExtra("noteID", 0),
                            binding.etTitle.text.toString(),
                            binding.etContent.text.toString()
                        ))
                }

                val myIntent = Intent(this, StartActivity::class.java)
                this.startActivity(myIntent)
            }
        }

    }
}