package com.putrapebrianonurba.wordsy.data.local.todo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ToDoEntity::class],
    version = 1,
    exportSchema = true
)
abstract class ToDoDatabase: RoomDatabase() {
    abstract val dao: ToDoDao
}