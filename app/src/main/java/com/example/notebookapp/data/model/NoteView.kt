package com.example.notebookapp.data.model

import androidx.room.DatabaseView

@DatabaseView(
    viewName = "note_view",
    value = """
        SELECT 
            titles.noteId AS id, 
            titles.title AS title, 
            descriptions.description AS description, 
            images.imageUrl AS imageUrl
        FROM titles
        INNER JOIN descriptions ON titles.noteId = descriptions.noteId
        INNER JOIN images ON titles.noteId = images.noteId
    """
)
data class NoteView(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String
)
