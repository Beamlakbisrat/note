package com.example.notebookapp.domain.usecase

import com.example.notebookapp.data.model.Title
import com.example.notebookapp.data.model.Description
import com.example.notebookapp.data.model.Image
import com.example.notebookapp.data.model.NoteView
import com.example.notebookapp.data.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNoteUseCase(private val repository: NoteRepository) {
    fun getAllTitles(): Flow<List<Title>> = repository.getAllTitles()
    fun getNoteViewById(noteId: Int): Flow<NoteView> = repository.getNoteViewById(noteId)
}
