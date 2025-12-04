package com.putrapebrianonurba.wordsy.presentation.notes.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putrapebrianonurba.wordsy.domain.model.Notes
import com.putrapebrianonurba.wordsy.domain.repository.NotesRepository
import com.putrapebrianonurba.wordsy.presentation.notes.add.state.NotesAddScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesAddScreenViewModel @Inject constructor(
    private val notesRepository: NotesRepository
): ViewModel() {
    // UI STATE
    private val _notesAddScreenUiState = MutableStateFlow(NotesAddScreenUiState())
    val notesAddScreenUiState = _notesAddScreenUiState.asStateFlow()

    // SEND/POST NEW NOTE TO DB
    fun postNewNotes() {
        val title = notesAddScreenUiState.value.addNewTitle
        val content = notesAddScreenUiState.value.addNewContent

        val note = Notes(
            title = title,
            content = content,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
        )

        viewModelScope.launch { notesRepository.insertNote(note) }
        _notesAddScreenUiState.update { it.copy(isEdited = false) }
    }

    // FUNCTIONALITY
    fun onValueChangeTitle(newValue: String) {
        _notesAddScreenUiState.update { it.copy(addNewTitle = newValue) }
        triggerSaveNote(newValue)
    }

    fun onValueChangeContent(newValue: String) {
        _notesAddScreenUiState.update { it.copy(addNewContent = newValue) }
        triggerSaveNote(newValue)
    }

    private fun triggerSaveNote(newValue: String) {
        if (newValue.isNotEmpty()) {
            _notesAddScreenUiState.update { it.copy(isEdited = true) }
        }
    }
}