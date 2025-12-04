package com.putrapebrianonurba.wordsy.presentation.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.putrapebrianonurba.wordsy.core.components.TodoCard
import com.putrapebrianonurba.wordsy.presentation.todo.components.ToDoBottomSheet
import com.putrapebrianonurba.wordsy.presentation.todo.components.ToDoMainScreenTopAppBar

@Composable
fun ToDoMainScreen(navController: NavController, viewModel: ToDoMainScreenViewModel) {
    val uiState by viewModel.todoMainScreenUiState.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            ToDoMainScreenTopAppBar(
                navController = navController,
                showAddSheetHandler = { viewModel.showAddSheet() }
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        // CONTAINER
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 10.dp
            )
        ) {
            // ALL TODOS ITEMS
            items(uiState.allToDos) { todo ->
                TodoCard(
                    todo = todo,
                    onCheckedChange = { isCompleted ->
                        viewModel.updateIsCompleted(todo.id, isCompleted)
                    },
                    onUpdateChange = { viewModel.showUpdatedSheet(todo) },
                )
            }
        }

        // BOTTOM SHEET
        ToDoBottomSheet(
            show = uiState.showSheet,
            textFieldValue = uiState.currentTitle,
            onValueChange = { viewModel.updateOnChangeTextField(it) },
            onDelete = { viewModel.deleteCurrentTodo() },
            onSave = { viewModel.saveTodo() },
            onDismiss = { viewModel.sheetDismissed() }
        )
    }
}