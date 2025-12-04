package com.putrapebrianonurba.wordsy.domain.model

data class ToDo(
    val id: Int = 0,
    val title: String,
    val isCompleted: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)
