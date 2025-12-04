package com.putrapebrianonurba.wordsy.presentation.notes.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putrapebrianonurba.wordsy.domain.repository.NotesRepository
import com.putrapebrianonurba.wordsy.presentation.notes.detail.state.NotesDetailScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesDetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository
): ViewModel() {
    private val _notesDetailScreenUiState = MutableStateFlow(NotesDetailScreenUiState())
    val notesDetailScreenUiState = _notesDetailScreenUiState.asStateFlow()

    private val noteId: Int = savedStateHandle["noteId"] ?: 0

    init { getNoteById() }

    // GETTING NOTE BY ID
    private fun getNoteById() {
        viewModelScope.launch {
            val note = notesRepository.getNoteById(noteId)

            note?.let { noteItems ->
                _notesDetailScreenUiState.update {
                    it.copy(
                        note = noteItems,
                        detailTitleState = noteItems.title,
                        detailContentState = noteItems.content
                    )
                }
            }
        }
    }

    // DELETE NOTES
    fun deleteNote() {
        viewModelScope.launch {
            val note = notesDetailScreenUiState.value.note
            note?.let { notesRepository.deleteNote(it) }
        }
    }

    // UPDATE NOTES
    fun updateNote() {
        val note = notesDetailScreenUiState.value.note
        val title = notesDetailScreenUiState.value.detailTitleState
        val content = notesDetailScreenUiState.value.detailContentState

        note?.let { note ->
            viewModelScope.launch {
                notesRepository.updateNote(
                    id = note.id,
                    title = title,
                    content = content,
                    updatedAt = System.currentTimeMillis()
                )
            }
        }

        _notesDetailScreenUiState.update {
            it.copy(isEdited = false)
        }
    }

    // fUNCTIONALITY
    fun onTitleChanged(newValue: String) {
        _notesDetailScreenUiState.update {
            it.copy(detailTitleState = newValue, isEdited = true)
        }
    }

    fun onContentChanged(newValue: String) {
        _notesDetailScreenUiState.update {
            it.copy(detailContentState = newValue, isEdited = true)
        }
    }

    fun showConfirmationDialog() {
        _notesDetailScreenUiState.update { it.copy(showConfirmationDialog = true) }
    }

    fun dismissDialog() {
        _notesDetailScreenUiState.update { it.copy(showConfirmationDialog = false) }
    }
}