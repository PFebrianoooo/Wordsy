package com.putrapebrianonurba.wordsy.presentation.todo.state

import com.putrapebrianonurba.wordsy.domain.model.ToDo

data class ToDoMainScreenUiState(
    // TODOS
    val allToDos: List<ToDo> = emptyList(),

    // UPDATED TODOS & DELETED TODOS
    val showSheet: Boolean = false,
    val currentTitle: String = "",
    val currentTodo: ToDo? = null

)
