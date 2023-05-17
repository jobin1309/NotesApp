package com.example.notesapp.di
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.data.NotesDao
import com.example.notesapp.data.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(context, NotesDatabase::class.java, "NoteDatabase").fallbackToDestructiveMigration().build()
    }


    @Provides
    fun provideNoteDao(notesDatabase: NotesDatabase): NotesDao {
        return notesDatabase.NotesDao()

    }
}