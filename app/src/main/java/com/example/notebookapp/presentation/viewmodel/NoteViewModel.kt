package com.example.notebookapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notebookapp.domain.usecase.GetNoteUseCase
import com.example.notebookapp.domain.usecase.UpdateNoteUseCase
import com.example.notebookapp.domain.usecase.DeleteNoteUseCase
import com.example.notebookapp.domain.usecase.InsertNoteUseCase
import com.example.notebookapp.data.model.NoteView
import com.example.notebookapp.data.model.Title
import com.example.notebookapp.data.model.Description
import com.example.notebookapp.data.model.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NoteViewModel(
    private val getNoteUseCase: GetNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
) : ViewModel() {

    private val _noteView = MutableStateFlow<NoteView?>(null)
    val noteView: StateFlow<NoteView?> get() = _noteView

    private val _titles = MutableStateFlow<List<Title>>(emptyList())
    val titles: StateFlow<List<Title>> get() = _titles

    fun getNoteView(noteId: Int) {
        viewModelScope.launch {
            getNoteUseCase.getNoteViewById(noteId).collect { _noteView.value = it }
        }
    }

    fun getAllTitles() {
        viewModelScope.launch {
            getNoteUseCase.getAllTitles().collect { _titles.value = it }
        }
    }

    fun updateTitle(noteId: Int, title: String) {
        viewModelScope.launch {
            updateNoteUseCase.updateTitle(noteId, title)
        }
    }

    fun updateDescription(noteId: Int, description: String) {
        viewModelScope.launch {
            updateNoteUseCase.updateDescription(noteId, description)
        }
    }
    fun updateImage(noteId: Int, imageUrl: String) {
        viewModelScope.launch {
            updateNoteUseCase.updateImage(noteId, imageUrl)
        }
    }


    fun deleteTitle(noteId: Int) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteTitle(noteId)
        }
    }

    fun deleteDescription(noteId: Int) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteDescription(noteId)
        }
    }

    fun deleteImage(noteId: Int) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteImage(noteId)
        }
    }

    fun insertTitle(title: Title) {
        viewModelScope.launch {
            insertNoteUseCase.insertTitle(title)
        }
    }

    fun insertDescription(description: Description) {
        viewModelScope.launch {
            insertNoteUseCase.insertDescription(description)
        }
    }

    fun insertImage(image: Image) {
        viewModelScope.launch {
            insertNoteUseCase.insertImage(image)
        }
    }
}

class NoteViewModelFactory(
    private val getNoteUseCase: GetNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(getNoteUseCase, updateNoteUseCase, deleteNoteUseCase, insertNoteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
