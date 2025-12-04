package com.putrapebrianonurba.wordsy.domain.repository

import com.putrapebrianonurba.wordsy.core.util.Resource
import com.putrapebrianonurba.wordsy.domain.model.Notes
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun insertNote(note: Notes)
    fun getAllNotes(): Flow<Resource<List<Notes>>>
    fun filterNotes(searchTerm: String, category: String): Flow<Resource<List<Notes>>>
    suspend fun getNoteById(id: Int): Notes?
    fun getAllTitles(): Flow<Resource<List<String>>>
    suspend fun updateNote(id: Int, title: String, content: String, updatedAt: Long)
    suspend fun deleteNote(note: Notes)
}
