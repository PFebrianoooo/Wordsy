package com.putrapebrianonurba.wordsy.data.local.todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    // METHOD: CREATE
    // EXPLANATION: Creating a new todos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toToDoEntity: ToDoEntity)

    // METHOD: READ
    // EXPLANATION: Getting all todos by their updatedAt in descending order
    @Query("SELECT * FROM todo ORDER BY updatedAt DESC")
    fun getAllToDo(): Flow<List<ToDoEntity>>

    // METHOD: UPDATE
    // EXPLANATION: Updating todos by id
    @Query("""
            UPDATE todo 
            SET title = :title,
                updatedAt = :updatedAt 
            WHERE id = :id
        """)
    suspend fun updateToDoById(id: Int, title: String, updatedAt: Long)

    @Query("""
        UPDATE todo
        SET isCompleted = :isCompleted
        WHERE id = :id
    """)
    suspend fun updateToDoIsCompleted(id: Int, isCompleted: Boolean)

    // METHOD: DELETE
    // EXPLANATION: Deleting todos
    @Delete
    suspend fun deleteToDo(note: ToDoEntity)
}