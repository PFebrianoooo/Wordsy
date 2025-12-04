package com.putrapebrianonurba.wordsy.presentation.notes.add

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
import com.putrapebrianonurba.wordsy.core.components.ContentTextField
import com.putrapebrianonurba.wordsy.core.components.TitleTextField
import com.putrapebrianonurba.wordsy.presentation.notes.add.components.AddScreenTopAppBar

@Composable
fun NotesAddScreen(navController: NavController, viewModel: NotesAddScreenViewModel) {
    val uiState by viewModel.notesAddScreenUiState.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            AddScreenTopAppBar(
                navController = navController,
                noteIsEdited = uiState.isEdited,
                postNewNoteHandler = { viewModel.postNewNotes() }
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        // CONTAINER
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),

        ) {
            item {
                // TITLE & CONTENT FIELD WRAPPER
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(18.dp))

                    // TITLE FIELD
                    TitleTextField(uiState.addNewTitle) {
                        viewModel.onValueChangeTitle(it)
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    // CONTENT FIELD
                    ContentTextField(uiState.addNewContent) {
                        viewModel.onValueChangeContent(it)
                    }
                }
            }
        }
    }
}