package com.example.notesapp.viewmodel

import android.app.Application
import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.util.appendPlaceholders
import com.example.notesapp.model.Notes
import com.example.notesapp.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(
    application: Application,
    private val mRepository: NoteRepository
) : AndroidViewModel(application) {

    val readAllNotes: LiveData<List<Notes>> = mRepository.readAllData


    fun addNote(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.insertNote(note)
        }
    }

    fun updateNote(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.updateNote(note)
        }
    }

    fun deleteNote(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.deleteNote(note)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.deleteAll()
        }


    }

    fun resetId() {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.resetId()
        }
    }


}