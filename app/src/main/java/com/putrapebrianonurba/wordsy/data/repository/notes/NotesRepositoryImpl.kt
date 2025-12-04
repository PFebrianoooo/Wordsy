package com.putrapebrianonurba.wordsy.data.repository.notes

import com.putrapebrianonurba.wordsy.core.util.Resource
import com.putrapebrianonurba.wordsy.data.local.notes.NotesDatabase
import com.putrapebrianonurba.wordsy.data.mappers.notes.toNotes
import com.putrapebrianonurba.wordsy.data.mappers.notes.toNotesEntity
import com.putrapebrianonurba.wordsy.domain.model.Notes
import com.putrapebrianonurba.wordsy.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesDatabase: NotesDatabase
): NotesRepository {
    override suspend fun insertNote(note: Notes) {
        notesDatabase.dao.insertNote(note.toNotesEntity())
    }

    override fun getAllNotes(): Flow<Resource<List<Notes>>> {
        return flow {
            try {
                notesDatabase.dao.getAllNotes().collect { list ->
                    emit(Resource.Success(
                        data = list.map { it.toNotes() }
                    ))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "No notes found"))
            }
        }
    }

    override fun filterNotes(searchTerm: String, category: String): Flow<Resource<List<Notes>>> {
        return flow {
            try {
                notesDatabase.dao.filterNotes(searchTerm, category).collect { list ->
                    emit(Resource.Success(
                        list.map { it.toNotes() }
                    ))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "No data found"))
            }
        }
    }

    override suspend fun getNoteById(id: Int): Notes? {
        return notesDatabase.dao.getNoteById(id).toNotes()
    }

    override fun getAllTitles(): Flow<Resource<List<String>>> {
        return flow {
            try {
                notesDatabase.dao.getAllTitles().collect { list ->
                    emit(Resource.Success(
                        list.map { it }
                    ))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "No titles found"))
            }
        }
    }

    override suspend fun updateNote(
        id: Int,
        title: String,
        content: String,
        updatedAt: Long
    ) {
        notesDatabase.dao.updateNote(id, title, content, updatedAt)
    }

    override suspend fun deleteNote(note: Notes) {
        notesDatabase.dao.deleteNote(note.toNotesEntity())
    }

}