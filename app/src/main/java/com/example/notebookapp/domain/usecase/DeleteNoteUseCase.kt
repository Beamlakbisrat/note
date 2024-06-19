package com.example.notebookapp.domain.usecase

import com.example.notebookapp.data.repository.NoteRepository

class DeleteNoteUseCase(private val repository: NoteRepository) {
    suspend fun deleteTitle(noteId: Int) = repository.deleteTitle(noteId)
    suspend fun deleteDescription(noteId: Int) = repository.deleteDescription(noteId)
    suspend fun deleteImage(noteId: Int) = repository.deleteImage(noteId)
}
