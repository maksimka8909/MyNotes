package com.example.myroom.dbRoom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {

    @Insert
    fun insertNote( note: Note)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<Note>>

    @Delete
    fun deleteNote(note : Note)

    @Update
    fun updateNote(note: Note)

}