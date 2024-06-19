package com.example.notebookapp.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.notebookapp.presentation.viewmodel.NoteViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

@Composable
fun NoteListView(navController: NavHostController, noteViewModel: NoteViewModel = viewModel()) {
    val titles by noteViewModel.titles.collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        noteViewModel.getAllTitles()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Text(
                    text = "My Notes",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(titles) { title ->
                NoteItem(title = title, onClick = {
                    navController.navigate("note_detail/${title.noteId}")
                })
            }
        }
    }

    if (showDialog) {
        CreateNoteDialog(noteViewModel = noteViewModel, onDismiss = { showDialog = false })
    }
}
