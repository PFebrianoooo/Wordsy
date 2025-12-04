package com.putrapebrianonurba.wordsy.presentation.notes.detail.state

import com.putrapebrianonurba.wordsy.domain.model.Notes

data class NotesDetailScreenUiState(
    // OBSERVER NOTE
    val note: Notes? = null,

    // EDITED STATE
    val isEdited: Boolean = false,
    val detailTitleState: String = "",
    val detailContentState: String = "",

    // DELETED STATE
    val showConfirmationDialog: Boolean = false
)
