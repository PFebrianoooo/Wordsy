package com.putrapebrianonurba.wordsy.presentation.notes.add.state

data class NotesAddScreenUiState(
    // INPUTS STATE
    val isEdited: Boolean = false,
    val addNewTitle: String = "",
    val addNewContent: String = "",
)
