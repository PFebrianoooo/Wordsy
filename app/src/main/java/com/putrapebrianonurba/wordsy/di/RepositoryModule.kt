package com.putrapebrianonurba.wordsy.di

import com.putrapebrianonurba.wordsy.data.repository.notes.NotesRepositoryImpl
import com.putrapebrianonurba.wordsy.data.repository.prefs.OnboardingRepositoryImpl
import com.putrapebrianonurba.wordsy.data.repository.todo.ToDoRepositoryImpl
import com.putrapebrianonurba.wordsy.domain.repository.NotesRepository
import com.putrapebrianonurba.wordsy.domain.repository.OnboardingRepository
import com.putrapebrianonurba.wordsy.domain.repository.ToDoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNotesRepository(
        notesRepositoryImpl: NotesRepositoryImpl
    ): NotesRepository

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository

    @Binds
    @Singleton
    abstract fun bindToDoRepository(
        toDoRepositoryImpl: ToDoRepositoryImpl
    ): ToDoRepository
}