package com.example.notebookapp.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.notebookapp.presentation.viewmodel.NoteViewModel


@Composable
fun NoteDetailView(navController: NavHostController, noteId: Int, noteViewModel: NoteViewModel = viewModel()) {
    Log.d("NoteDetailView", "Note ID: $noteId")

    val noteView by noteViewModel.noteView.collectAsState()
    var isEditing by remember { mutableStateOf(false) }

    LaunchedEffect(noteId) {
        Log.d("NoteDetailView", "Fetching data for Note ID: $noteId")
        noteViewModel.getNoteView(noteId)
    }

    if (noteView == null) {
        Log.d("NoteDetailView", "Note data not found, navigating back")
        LaunchedEffect(Unit) {
            navController.navigateUp()
        }
        return
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isEditing) {
                TextField(
                    value = noteView!!.title,
                    onValueChange = { title -> noteViewModel.updateTitle(noteId, title) },
                    label = { Text("Title") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = noteView!!.description,
                    onValueChange = { description -> noteViewModel.updateDescription(noteId, description) },
                    label = { Text("Description") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = noteView!!.imageUrl,
                    onValueChange = { imageUrl -> noteViewModel.updateImage(noteId, imageUrl) },
                    label = { Text("Image URL") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    Log.d("NoteDetailView", "Updating note with title: ${noteView!!.title}, description: ${noteView!!.description}, imageUrl: ${noteView!!.imageUrl}")
                    try {
                        noteViewModel.updateTitle(noteId, noteView!!.title)
                        noteViewModel.updateDescription(noteId, noteView!!.description)
                        noteViewModel.updateImage(noteId, noteView!!.imageUrl)
                        isEditing = false
                        Log.d("NoteDetailView", "Note updated successfully")
                    } catch (e: Exception) {
                        Log.e("NoteDetailView", "Error updating note: ${e.message}", e)
                    }
                }) {
                    Text("Save")
                }
            } else {
                Text(
                    text = noteView!!.title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = noteView!!.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(8.dp))
                val painter: Painter = rememberAsyncImagePainter(model = noteView!!.imageUrl)
                Image(
                    painter = painter,
                    contentDescription = "Note Image",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        if (!isEditing) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { isEditing = true }) {
                    Text("Edit")
                }
                Button(onClick = {
                    try {
                        noteViewModel.deleteTitle(noteId)
                        noteViewModel.deleteDescription(noteId)
                        noteViewModel.deleteImage(noteId)
                        navController.navigateUp()
                        Log.d("NoteDetailView", "Note deleted successfully")
                    } catch (e: Exception) {
                        Log.e("NoteDetailView", "Error deleting note: ${e.message}", e)
                    }
                }) {
                    Text("Delete")
                }
            }
        }
    }
}
