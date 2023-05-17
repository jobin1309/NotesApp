package com.example.notesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapp.model.Notes


@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun NotesDao():  NotesDao


}