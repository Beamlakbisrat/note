package com.example.notebookapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class Image(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val noteId: Int,
    val imageUrl: String
)
