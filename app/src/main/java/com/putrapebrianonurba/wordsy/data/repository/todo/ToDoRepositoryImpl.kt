package com.putrapebrianonurba.wordsy.data.repository.todo

import com.putrapebrianonurba.wordsy.core.util.Resource
import com.putrapebrianonurba.wordsy.data.local.todo.ToDoDatabase
import com.putrapebrianonurba.wordsy.data.mappers.todo.toToDo
import com.putrapebrianonurba.wordsy.data.mappers.todo.toToDoEntity
import com.putrapebrianonurba.wordsy.domain.model.ToDo
import com.putrapebrianonurba.wordsy.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val toDoDatabase: ToDoDatabase
): ToDoRepository {
    override suspend fun insertToDo(toDo: ToDo) {
        toDoDatabase.dao.insertToDo(toDo.toToDoEntity())
    }

    override fun getAllToDos(): Flow<Resource<List<ToDo>>> {
        return flow {
            try {
                toDoDatabase.dao.getAllToDo().collect { list ->
                    emit(Resource.Success(
                        data = list.map { it.toToDo() }
                    ))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "No todos found"))
            }
        }
    }

    override suspend fun updateToDoById(
        id: Int,
        title: String,
        updatedAt: Long
    ) {
        toDoDatabase.dao.updateToDoById(id, title, updatedAt)
    }

    override suspend fun updateToDoIsCompleted(
        id: Int,
        isCompleted: Boolean
    ) {
        toDoDatabase.dao.updateToDoIsCompleted(id, isCompleted)
    }

    override suspend fun deleteToDo(toDo: ToDo) {
        toDoDatabase.dao.deleteToDo(toDo.toToDoEntity())
    }
}