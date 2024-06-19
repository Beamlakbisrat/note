package com.example.notebookapp.data.source

import androidx.room.*
import com.example.notebookapp.data.model.Title
import com.example.notebookapp.data.model.Description
import com.example.notebookapp.data.model.Image
import com.example.notebookapp.data.model.NoteView
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM titles WHERE noteId = :noteId")
    fun getTitleByNoteId(noteId: Int): Flow<Title>

    @Query("SELECT * FROM descriptions WHERE noteId = :noteId")
    fun getDescriptionByNoteId(noteId: Int): Flow<Description>

    @Query("SELECT * FROM images WHERE noteId = :noteId")
    fun getImageByNoteId(noteId: Int): Flow<Image>

    @Query("SELECT * FROM titles ORDER BY noteId DESC")
    fun getAllTitles(): Flow<List<Title>>

    @Query("SELECT * FROM note_view WHERE id = :noteId")
    fun getNoteViewById(noteId: Int): Flow<NoteView>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTitle(title: Title)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDescription(description: Description)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: Image)

    @Query("UPDATE titles SET title = :title WHERE noteId = :noteId")
    suspend fun updateTitle(noteId: Int, title: String)

    @Query("UPDATE descriptions SET description = :description WHERE noteId = :noteId")
    suspend fun updateDescription(noteId: Int, description: String)

    @Query("UPDATE images SET imageUrl = :imageUrl WHERE noteId = :noteId")
    suspend fun updateImage(noteId: Int, imageUrl: String)

    @Query("DELETE FROM titles WHERE noteId = :noteId")
    suspend fun deleteTitle(noteId: Int)

    @Query("DELETE FROM descriptions WHERE noteId = :noteId")
    suspend fun deleteDescription(noteId: Int)

    @Query("DELETE FROM images WHERE noteId = :noteId")
    suspend fun deleteImage(noteId: Int)
}
