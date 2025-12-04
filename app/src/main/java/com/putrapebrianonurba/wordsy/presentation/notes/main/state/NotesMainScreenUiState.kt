package com.putrapebrianonurba.wordsy.presentation.notes.main.state

import com.putrapebrianonurba.wordsy.domain.model.Notes
import com.putrapebrianonurba.wordsy.domain.model.ToDo

data class NotesMainScreenUiState(
    // INPUTS SECTION
    val searchTerm: String = "",
    val showSearchField: Boolean = false,
    val allSearchedNotes: List<Notes> = emptyList(),

    // CATEGORY SECTION
    val category: List<String> = listOf("All Notes"),
    val selectedCategory: String = "All Notes",

    // TODOS SECTION
    val allToDos: List<ToDo> = emptyList(),

    // NOTES SECTION
    val allNotes: List<Notes> = emptyList(),
)