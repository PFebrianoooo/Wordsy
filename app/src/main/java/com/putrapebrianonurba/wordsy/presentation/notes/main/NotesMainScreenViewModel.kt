package com.putrapebrianonurba.wordsy.presentation.notes.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putrapebrianonurba.wordsy.core.util.Resource
import com.putrapebrianonurba.wordsy.domain.repository.NotesRepository
import com.putrapebrianonurba.wordsy.domain.repository.ToDoRepository
import com.putrapebrianonurba.wordsy.presentation.notes.main.state.NotesMainScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesMainScreenViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
    private val toDoRepository: ToDoRepository
): ViewModel() {
    // UI State
    private val _notesMainScreenUiState = MutableStateFlow(NotesMainScreenUiState())
    val notesMainScreenUiState = _notesMainScreenUiState.asStateFlow()

    // Debounce
    private var searchJob: Job? = null

    init {
        getAllNotes()
        getAllCategory()
        getAllTodos()
    }

    // NOTES FEATURE
    // GETTING ALL NOTES
    private fun getAllNotes() {
        viewModelScope.launch {
            notesRepository.getAllNotes().collectLatest { result ->
                when(result) {
                    is Resource.Error -> {
                        Log.d("NotesMainScreenVM", result.message.toString())
                    }
                    is Resource.Success -> {
                        result.data?.let { allNotes ->
                            _notesMainScreenUiState.update {
                                it.copy(allNotes = allNotes)
                            }
                        }
                    }
                }
            }
        }
    }

    // GETTING ALL CATEGORY
    private fun getAllCategory() {
        viewModelScope.launch {
            notesRepository.getAllTitles().collectLatest { result ->
                when(result) {
                    is Resource.Error -> {
                        Log.d("NotesMainScreenVM", result.message.toString())
                    }
                    is Resource.Success -> {
                        result.data?.let { allCategory ->
                            _notesMainScreenUiState.update {
                                it.copy(category = listOf("All Notes") + allCategory)
                            }
                        }
                    }
                }
            }
        }
    }

    // GETTING DATA BASED ON SEARCH TERM & CATEGORY SELECTED
    private fun filterNotes() {
        viewModelScope.launch {
            notesRepository.filterNotes(
                searchTerm = _notesMainScreenUiState.value.searchTerm,
                category = _notesMainScreenUiState.value.selectedCategory
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        Log.d("NotesMainScreenVM", result.message.toString())
                    }
                    is Resource.Success -> {
                        result.data?.let { notes ->
                            _notesMainScreenUiState.update { it.copy(allNotes = notes) }
                        }
                    }
                }
            }
        }
    }

    // TODOS FEATURE
    // GETTING ALL TODOS
    private fun getAllTodos() {
        viewModelScope.launch {
            toDoRepository.getAllToDos().collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        Log.d("NotesMainScreenVM", result.message.toString())
                    }
                    is Resource.Success-> {
                        result.data?.let { allTodos ->
                            _notesMainScreenUiState.update {
                                it.copy(allToDos = allTodos)
                            }
                        }
                    }
                }
            }
        }
    }


    // FUNCTIONALITY
    fun toggleShowSearchForm() {
        val newVisible = !_notesMainScreenUiState.value.showSearchField

        _notesMainScreenUiState.update { it.copy(showSearchField = newVisible) }

        if (!newVisible) {
            _notesMainScreenUiState.update { it.copy(searchTerm = "", selectedCategory = "All Notes") }
        }

        getAllNotes()
    }

    fun updateOnChangeSearchField(newValue: String) {
        _notesMainScreenUiState.update { it.copy(searchTerm = newValue) }

        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(300)
            filterNotes()
        }
        filterNotes()
    }

    fun updateOnCategoryChip(newValue: String) {
        _notesMainScreenUiState.update { it.copy(selectedCategory = newValue) }
        filterNotes()
    }

    fun updateIsCompleted(todoId: Int, isCompleted: Boolean) {
        viewModelScope.launch {
            toDoRepository.updateToDoIsCompleted(todoId, isCompleted)
        }
    }
}