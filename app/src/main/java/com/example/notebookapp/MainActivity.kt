package com.example.notebookapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.notebookapp.navigation.NavGraph
import com.example.notebookapp.ui.theme.NotebookAppTheme
import com.example.notebookapp.presentation.viewmodel.NoteViewModel
import com.example.notebookapp.data.source.NoteDatabase
import com.example.notebookapp.data.repository.NoteRepositoryImpl
import com.example.notebookapp.domain.usecase.GetNoteUseCase
import com.example.notebookapp.domain.usecase.UpdateNoteUseCase
import com.example.notebookapp.domain.usecase.DeleteNoteUseCase
import com.example.notebookapp.domain.usecase.InsertNoteUseCase
import com.example.notebookapp.presentation.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = NoteDatabase.getDatabase(this)
        val repository = NoteRepositoryImpl(database.noteDao())
        val getNoteUseCase = GetNoteUseCase(repository)
        val updateNoteUseCase = UpdateNoteUseCase(repository)
        val deleteNoteUseCase = DeleteNoteUseCase(repository)
        val insertNoteUseCase = InsertNoteUseCase(repository)
        val factory = NoteViewModelFactory(getNoteUseCase, updateNoteUseCase, deleteNoteUseCase, insertNoteUseCase)
        setContent {
            NotebookAppTheme {
                val noteViewModel: NoteViewModel = viewModel(factory = factory)
                val navController = rememberNavController()
                NavGraph(navController, noteViewModel)
            }
        }
    }
}
