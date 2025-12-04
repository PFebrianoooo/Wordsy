    package com.putrapebrianonurba.wordsy.data.local.notes

    import androidx.room.Dao
    import androidx.room.Delete
    import androidx.room.Insert
    import androidx.room.OnConflictStrategy
    import androidx.room.Query
    import kotlinx.coroutines.flow.Flow

    @Dao
    interface NotesDao {
        // METHOD: CREATE
        // EXPLANATION: Creating a new note
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertNote(note: NotesEntity)

        // METHOD: READ
        // EXPLANATION: Getting all notes by their updatedAt in descending order
        @Query("SELECT * FROM notes ORDER BY updatedAt DESC")
        fun getAllNotes(): Flow<List<NotesEntity>>

        // EXPLANATION: Getting all notes by search term and category
        @Query("""
            SELECT * FROM notes
            WHERE (:searchTerm IS NULL OR :searchTerm = '' 
            OR title LIKE '%' || :searchTerm || '%' COLLATE NOCASE
            OR content LIKE '%' || :searchTerm || '%' COLLATE NOCASE)
            AND (:category = 'All Notes' OR title = :category)
            ORDER BY updatedAt DESC
        """)
        fun filterNotes(searchTerm: String, category: String): Flow<List<NotesEntity>>

        // EXPLANATION: Getting note by id
        @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
        suspend fun getNoteById(id: Int): NotesEntity

        // EXPLANATION: Getting all titles in descending order
        @Query("SELECT DISTINCT title FROM notes ORDER BY updatedAt DESC")
        fun getAllTitles(): Flow<List<String>>

        // METHOD: UPDATE
        // EXPLANATION: Updating note by id
        @Query("""
            UPDATE notes 
            SET title = :title, 
                content = :content, 
                updatedAt = :updatedAt 
            WHERE id = :id
        """)
        suspend fun updateNote(id: Int, title: String, content: String, updatedAt: Long)

        // METHOD: DELETE
        // EXPLANATION: Deleting note
        @Delete
        suspend fun deleteNote(note: NotesEntity)
    }