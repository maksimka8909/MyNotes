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
    suspend fun insertNote( note: Note)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Note>

    @Delete
    suspend fun deleteNote(note : Note)

    @Update
    suspend fun updateNote(note: Note)

}