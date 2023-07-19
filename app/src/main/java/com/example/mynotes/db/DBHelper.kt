package com.example.mynotes.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context,Note.DATABASE_NAME,
    null, Note.DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Note.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(Note.SQL_DELETE_TABLE)
        onCreate(db)
    }
}