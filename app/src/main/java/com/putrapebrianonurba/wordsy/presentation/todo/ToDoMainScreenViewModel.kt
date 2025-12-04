package com.putrapebrianonurba.wordsy.presentation.todo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putrapebrianonurba.wordsy.core.util.Resource
import com.putrapebrianonurba.wordsy.domain.model.ToDo
import com.putrapebrianonurba.wordsy.domain.repository.ToDoRepository
import com.putrapebrianonurba.wordsy.presentation.todo.state.ToDoMainScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoMainScreenViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
): ViewModel() {
    private val _todoMainScreenUiState = MutableStateFlow(ToDoMainScreenUiState())
    val todoMainScreenUiState = _todoMainScreenUiState.asStateFlow()

    init { getAllTodos() }

    // GETTING ALL TODOS
    private fun getAllTodos() {
        viewModelScope.launch {
            toDoRepository.getAllToDos().collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        Log.d("ToDoMainScreenViewModel", result.message.toString())
                    }
                    is Resource.Success-> {
                        result.data?.let { allTodos ->
                            _todoMainScreenUiState.update {
                                it.copy(allToDos = allTodos)
                            }
                        }
                    }
                }
            }
        }
    }

    fun saveTodo() {
        val todo = todoMainScreenUiState.value.currentTodo
        val title = todoMainScreenUiState.value.currentTitle
        val currentTime = System.currentTimeMillis()

        viewModelScope.launch {
            if (todo == null) {
                toDoRepository.insertToDo(
                    ToDo(
                        title = title,
                        isCompleted = false,
                        createdAt = currentTime,
                        updatedAt = currentTime
                    )
                )
            } else {
                toDoRepository.updateToDoById(todo.id, title, currentTime)
            }
        }
        sheetDismissed()
    }

    fun deleteCurrentTodo() {
        todoMainScreenUiState.value.currentTodo?.let { todo ->
            viewModelScope.launch {
                toDoRepository.deleteToDo(todo)
                sheetDismissed()
            }
        }
    }

    // FUNCTIONALITY
    fun showAddSheet() {
        _todoMainScreenUiState.update {
            it.copy(
                showSheet = true,
                currentTitle = "",
                currentTodo = null
        ) }
    }

    fun showUpdatedSheet(toDo: ToDo) {
        _todoMainScreenUiState.update {
            it.copy(
                showSheet = true,
                currentTitle = toDo.title,
                currentTodo = toDo
            )
        }
    }
    fun sheetDismissed() {
        _todoMainScreenUiState.update { it.copy(showSheet = false) }
    }

    fun updateOnChangeTextField(newValue: String) {
        _todoMainScreenUiState.update { it.copy(currentTitle = newValue) }
    }

    fun updateIsCompleted(todoId: Int, isCompleted: Boolean) {
        viewModelScope.launch {
            toDoRepository.updateToDoIsCompleted(todoId, isCompleted)
        }
    }
}