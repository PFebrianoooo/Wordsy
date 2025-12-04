package com.putrapebrianonurba.wordsy.domain.model

data class Notes(
    val id: Int = 0,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long
)
