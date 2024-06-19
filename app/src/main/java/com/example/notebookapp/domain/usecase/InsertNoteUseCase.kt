package com.example.notebookapp.domain.usecase

import com.example.notebookapp.data.repository.NoteRepository
import com.example.notebookapp.data.model.Title
import com.example.notebookapp.data.model.Description
import com.example.notebookapp.data.model.Image

class InsertNoteUseCase(private val repository: NoteRepository) {
    suspend fun insertTitle(title: Title) = repository.insertTitle(title)
    suspend fun insertDescription(description: Description) = repository.insertDescription(description)
    suspend fun insertImage(image: Image) = repository.insertImage(image)
}
