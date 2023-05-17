package com.example.notesapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName ="Notes_table")
data class Notes(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var mainNotes: String,
    var description: String
) : Parcelable{

}
