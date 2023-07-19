package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynotes.databinding.ActivityMainBinding
import com.example.mynotes.db.DBManager

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val dbManager = DBManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        dbManager.openDb()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnAddNote.setOnClickListener{
            dbManager.insertToDb(binding.etTitle.text.toString(),
                binding.etContent.text.toString())
            val myIntent = Intent(this, StartActivity::class.java)
            this.startActivity(myIntent)

        }

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }
}