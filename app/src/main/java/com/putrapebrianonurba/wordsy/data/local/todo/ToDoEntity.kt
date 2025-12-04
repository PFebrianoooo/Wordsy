package com.putrapebrianonurba.wordsy.data.local.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val isCompleted: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)
