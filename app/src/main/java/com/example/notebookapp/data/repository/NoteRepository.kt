package com.example.notebookapp.data.repository

import com.example.notebookapp.data.model.Title
import com.example.notebookapp.data.model.Description
import com.example.notebookapp.data.model.Image
import com.example.notebookapp.data.model.NoteView
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllTitles(): Flow<List<Title>>
    fun getNoteViewById(noteId: Int): Flow<NoteView>
    suspend fun updateTitle(noteId: Int, title: String)
    suspend fun updateDescription(noteId: Int, description: String)
    suspend fun updateImage(noteId: Int, imageUrl: String)
    suspend fun deleteTitle(noteId: Int)
    suspend fun deleteDescription(noteId: Int)
    suspend fun deleteImage(noteId: Int)
    suspend fun insertTitle(title: Title)
    suspend fun insertDescription(description: Description)
    suspend fun insertImage(image: Image)
}
