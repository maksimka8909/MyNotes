package com.example.myroom


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.example.myroom.databinding.ActivityMainBinding
import com.example.myroom.dbRoom.MainDb
import com.example.myroom.dbRoom.Note

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db : MainDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MainDb.getDb(this)
        binding.btnAddNote.setOnClickListener {
            val note = Note(null,binding.etTitle.text.toString(),
            binding.etContent.text.toString())

            Thread{
                db.getDao().insertNote(note)
            }.start()
            val myIntent = Intent(this, StartActivity::class.java)
            this.startActivity(myIntent)
        }
    }
}