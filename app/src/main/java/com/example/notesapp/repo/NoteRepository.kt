package com.example.notesapp.repo

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import com.example.notesapp.data.NotesDao
import com.example.notesapp.model.Notes
import javax.inject.Inject


class NoteRepository @Inject constructor(private val noteDao: NotesDao) {


    val readAllData: LiveData<List<Notes>> = noteDao.readALl()


    suspend fun insertNote(note: Notes) {
        noteDao.insertNote(note)
    }

    suspend fun updateNote(note: Notes) {
        noteDao.updateNote(note)
    }


    suspend fun deleteNote(note: Notes) {
        noteDao.deleteNote(note)
    }

    suspend fun deleteAll() {
        noteDao.deleteAll()
    }

    suspend fun resetId() {
        noteDao.resetId()
    }




}