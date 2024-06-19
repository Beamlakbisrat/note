package com.example.notebookapp.presentation.ui

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.notebookapp.data.model.Description
import com.example.notebookapp.data.model.Image
import com.example.notebookapp.data.model.Title
import com.example.notebookapp.presentation.viewmodel.NoteViewModel

@Composable
fun CreateNoteDialog(noteViewModel: NoteViewModel, onDismiss: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUrl = it.toString()
        }
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Create Note") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = imageUrl,
                    onValueChange = { imageUrl = it },
                    label = { Text("Image URL") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                if (title.isNotBlank() && description.isNotBlank() && imageUrl.isNotBlank()) {
                    val noteId = System.currentTimeMillis().toInt()
                    noteViewModel.insertTitle(Title(noteId = noteId, title = title))
                    noteViewModel.insertDescription(Description(noteId = noteId, description = description))
                    noteViewModel.insertImage(Image(noteId = noteId, imageUrl = imageUrl))
                    onDismiss()
                } else {
                    Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}
