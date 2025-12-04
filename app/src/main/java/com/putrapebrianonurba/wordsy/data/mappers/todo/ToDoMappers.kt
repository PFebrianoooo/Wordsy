package com.putrapebrianonurba.wordsy.data.mappers.todo

import com.putrapebrianonurba.wordsy.data.local.todo.ToDoEntity
import com.putrapebrianonurba.wordsy.domain.model.ToDo

fun ToDo.toToDoEntity(): ToDoEntity {
    return ToDoEntity(
        id = id,
        title = title,
        isCompleted = isCompleted,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun ToDoEntity.toToDo(): ToDo {
    return ToDo(
        id = id,
        title = title,
        isCompleted = isCompleted,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}