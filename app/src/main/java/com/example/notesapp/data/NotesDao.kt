package com.example.notesapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.model.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Query("DELETE FROM Notes_table")
    suspend fun deleteAll()

    @Query("DELETE FROM sqlite_sequence WHERE NAME='Notes_table'")
    suspend fun resetId()

    @Query("SELECT * FROM Notes_table")
    fun readALl(): LiveData<List<Notes>>


}