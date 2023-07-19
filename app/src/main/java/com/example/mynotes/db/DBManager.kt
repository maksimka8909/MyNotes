package com.example.mynotes.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class DBManager(val context: Context) {
    val dbHelper = DBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db =  dbHelper.writableDatabase
    }

    fun closeDb(){
        dbHelper.close()
    }

    fun insertToDb(title: String, content: String){
        val values = ContentValues().apply {
            put(Note.COLUMN_NAME_TITLE, title)
            put(Note.COLUMN_NAME_CONTENT, content)
        }
        db?.insert(Note.TABLE_NAME,null, values)
    }

    fun deleteFromDb(idNote: Int ){
        db?.delete(Note.TABLE_NAME,"${BaseColumns._ID}=$idNote", null)
    }

    fun updateFromDb(idNote: Int, title: String, content: String){
        val values = ContentValues().apply {
            put(Note.COLUMN_NAME_TITLE, title)
            put(Note.COLUMN_NAME_CONTENT, content)
        }
        db?.update(Note.TABLE_NAME, values,"${BaseColumns._ID}=$idNote",null)
    }

    fun readDBData(): ArrayList<NoteClass>{
        val notes = ArrayList<NoteClass>()
        val cursor = db?.query(Note.TABLE_NAME, null, null,
            null, null, null, null)

        while (cursor?.moveToNext()!!){
                val dataID = cursor.getString(cursor.getColumnIndexOrThrow(BaseColumns._ID)).toInt()
                val dataTitle = cursor.getString(cursor.getColumnIndexOrThrow(Note.COLUMN_NAME_TITLE))
                val dataContent = cursor.getString(cursor.getColumnIndexOrThrow(Note.COLUMN_NAME_CONTENT))
                notes.add(NoteClass(dataID,dataTitle,dataContent))
        }
        cursor.close()

        return notes
    }
}