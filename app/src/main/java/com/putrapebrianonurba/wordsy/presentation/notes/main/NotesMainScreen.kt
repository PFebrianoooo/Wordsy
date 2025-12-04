
package com.putrapebrianonurba.wordsy.presentation.notes.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.putrapebrianonurba.wordsy.core.components.CircledButton
import com.putrapebrianonurba.wordsy.presentation.navigation.routes.GraphRoute
import com.putrapebrianonurba.wordsy.presentation.navigation.routes.NotesScreen
import com.putrapebrianonurba.wordsy.presentation.navigation.routes.buildRoute
import com.putrapebrianonurba.wordsy.presentation.notes.main.components.CategorySection
import com.putrapebrianonurba.wordsy.presentation.notes.main.components.MainScreenTopAppBar
import com.putrapebrianonurba.wordsy.presentation.notes.main.components.NotesSection
import com.putrapebrianonurba.wordsy.presentation.notes.main.components.ToDoSection

@Composable
fun NotesMainScreen(navController: NavController, viewModel: NotesMainScreenViewModel) {
    val uiState by viewModel.notesMainScreenUiState.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            MainScreenTopAppBar(
                showSearchField = uiState.showSearchField,
                searchTerm = uiState.searchTerm,
                toggleShowSearchFormHandler = { viewModel.toggleShowSearchForm() },
                searchTermValueOnChange = { viewModel.updateOnChangeSearchField(it) }
            )
        },
        floatingActionButton = {
            CircledButton(Icons.Default.Add, 0f) {
                navController.navigate(NotesScreen.Add.route)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = Color.White
    ) { innerPadding ->
        // CONTAINER
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            // CATEGORY SECTION
            item {
                CategorySection(
                    categories = uiState.category,
                    selectedCategory = uiState.selectedCategory,
                    onCategorySelected = { viewModel.updateOnCategoryChip(it) },
                )
            }

            // TO DO SECTION
            item {
                AnimatedVisibility(
                    visible = (!uiState.showSearchField && uiState.selectedCategory == "All Notes"),
                    enter = slideInHorizontally(
                        initialOffsetX = { -it },
                    ) + fadeIn(),
                    exit = slideOutHorizontally(
                        targetOffsetX = { -it },
                    ) + fadeOut()
                ) {
                    ToDoSection(
                        todos = uiState.allToDos,
                        onCheckedChange = { todoId, isChecked ->
                            viewModel.updateIsCompleted(todoId, isChecked)
                        },
                        handleNavigateScreen = {
                            navController.navigate(GraphRoute.TODO)
                        }
                    )
                }
            }

            // NOTES SECTION
            item {
                NotesSection(uiState.allNotes) {noteId ->
                    navController.navigate(NotesScreen.Detail.buildRoute(noteId))
                }
            }
        }
    }
}