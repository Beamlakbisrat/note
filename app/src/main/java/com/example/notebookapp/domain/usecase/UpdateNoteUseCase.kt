package com.example.notebookapp.domain.usecase

import com.example.notebookapp.data.repository.NoteRepository

class UpdateNoteUseCase(private val repository: NoteRepository) {
    suspend fun updateTitle(noteId: Int, title: String) = repository.updateTitle(noteId, title)
    suspend fun updateDescription(noteId: Int, description: String) = repository.updateDescription(noteId, description)
    suspend fun updateImage(noteId: Int, imageUrl: String) = repository.updateImage(noteId, imageUrl)
}
