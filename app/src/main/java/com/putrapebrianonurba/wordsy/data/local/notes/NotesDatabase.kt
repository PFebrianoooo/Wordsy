package com.putrapebrianonurba.wordsy.data.local.notes

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NotesEntity::class],
    version = 1,
    exportSchema = true
)
abstract class NotesDatabase: RoomDatabase() {
    abstract val dao: NotesDao
}