package com.putrapebrianonurba.wordsy.domain.repository

import com.putrapebrianonurba.wordsy.core.util.Resource
import com.putrapebrianonurba.wordsy.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    suspend fun insertToDo(toDo: ToDo)
    fun getAllToDos(): Flow<Resource<List<ToDo>>>
    suspend fun updateToDoById(id: Int, title: String, updatedAt: Long)
    suspend fun updateToDoIsCompleted(id: Int, isCompleted: Boolean)
    suspend fun deleteToDo(toDo: ToDo)
}