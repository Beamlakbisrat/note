package com.example.notebookapp.data.repository

import com.example.notebookapp.data.model.Title
import com.example.notebookapp.data.model.Description
import com.example.notebookapp.data.model.Image
import com.example.notebookapp.data.model.NoteView
import com.example.notebookapp.data.source.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override fun getAllTitles(): Flow<List<Title>> {
        return noteDao.getAllTitles()
    }

    override fun getNoteViewById(noteId: Int): Flow<NoteView> {
        return noteDao.getNoteViewById(noteId)
    }

    override suspend fun updateTitle(noteId: Int, title: String) {
        noteDao.updateTitle(noteId, title)
    }

    override suspend fun updateDescription(noteId: Int, description: String) {
        noteDao.updateDescription(noteId, description)
    }

    override suspend fun updateImage(noteId: Int, imageUrl: String) {
        noteDao.updateImage(noteId, imageUrl)
    }

    override suspend fun deleteTitle(noteId: Int) {
        noteDao.deleteTitle(noteId)
    }

    override suspend fun deleteDescription(noteId: Int) {
        noteDao.deleteDescription(noteId)
    }

    override suspend fun deleteImage(noteId: Int) {
        noteDao.deleteImage(noteId)
    }


override suspend fun insertTitle(title: Title) {
        noteDao.insertTitle(title)
    }

    override suspend fun insertDescription(description: Description) {
        noteDao.insertDescription(description)
    }

    override suspend fun insertImage(image: Image) {
        noteDao.insertImage(image)
    }
}
