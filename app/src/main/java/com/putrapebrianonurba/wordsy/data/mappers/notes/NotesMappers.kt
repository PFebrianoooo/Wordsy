package com.putrapebrianonurba.wordsy.data.mappers.notes

import com.putrapebrianonurba.wordsy.data.local.notes.NotesEntity
import com.putrapebrianonurba.wordsy.domain.model.Notes

fun Notes.toNotesEntity(): NotesEntity {
    return NotesEntity(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun NotesEntity.toNotes(): Notes {
    return Notes(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}