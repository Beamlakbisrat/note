package com.example.notebookapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notebookapp.presentation.ui.NoteDetailView
import com.example.notebookapp.presentation.ui.NoteListView
import com.example.notebookapp.presentation.viewmodel.NoteViewModel

@Composable
fun NavGraph(navController: NavHostController, noteViewModel: NoteViewModel = viewModel()) {
    NavHost(navController = navController, startDestination = "note_list") {
        composable("note_list") {
            NoteListView(navController = navController, noteViewModel = noteViewModel)
        }
        composable("note_detail/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toInt() ?: -1
            NoteDetailView(navController = navController, noteId = noteId, noteViewModel = noteViewModel)
        }
    }
}
