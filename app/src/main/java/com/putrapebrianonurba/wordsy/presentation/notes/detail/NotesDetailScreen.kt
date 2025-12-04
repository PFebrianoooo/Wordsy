package com.putrapebrianonurba.wordsy.presentation.notes.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.putrapebrianonurba.wordsy.core.components.ConfirmationDialog
import com.putrapebrianonurba.wordsy.core.components.ContentTextField
import com.putrapebrianonurba.wordsy.core.components.TitleTextField
import com.putrapebrianonurba.wordsy.presentation.notes.detail.components.DetailScreenTopAppBar

@Composable
fun NotesDetailScreen(navController: NavController, viewModel: NotesDetailScreenViewModel) {
    val uiState by viewModel.notesDetailScreenUiState.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            DetailScreenTopAppBar(
                navController = navController,
                noteIsEdited = uiState.isEdited,
                showDialoghandler = { viewModel.showConfirmationDialog() },
                updateNoteHandler = { viewModel.updateNote() }
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        // CONTAINER
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            item {
                // TITLE & FIELD WRAPPER
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(18.dp))

                    // TITLE FIELD
                    TitleTextField(uiState.detailTitleState) {
                        viewModel.onTitleChanged(it)
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // CONTENT FIELD
                    ContentTextField(uiState.detailContentState) {
                        viewModel.onContentChanged(it)
                    }
                }
            }
        }
    }

    if (uiState.showConfirmationDialog) {
        ConfirmationDialog(
            handleOnDismiss = { viewModel.dismissDialog() },
            handleDismissButton = { viewModel.dismissDialog() },
            handleConfirmButton = {
                viewModel.deleteNote()
                navController.popBackStack()
            }
        )
    }
}