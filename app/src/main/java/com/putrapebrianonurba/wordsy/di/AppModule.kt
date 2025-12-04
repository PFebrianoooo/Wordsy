package com.putrapebrianonurba.wordsy.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.putrapebrianonurba.wordsy.data.local.notes.NotesDatabase
import com.putrapebrianonurba.wordsy.data.local.todo.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPrefs(
        @ApplicationContext app: Context
    ): SharedPreferences = app.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideNotesDatabase(app: Application): NotesDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = NotesDatabase::class.java,
            name = "notes.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideToDoDatabase(app: Application): ToDoDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = ToDoDatabase::class.java,
            name = "todo.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}