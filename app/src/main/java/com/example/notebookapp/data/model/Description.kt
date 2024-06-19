package com.example.notebookapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "descriptions")
data class Description(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val noteId: Int,
    val description: String
)
