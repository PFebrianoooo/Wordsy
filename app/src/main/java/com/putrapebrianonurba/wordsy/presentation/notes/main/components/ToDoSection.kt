package com.putrapebrianonurba.wordsy.presentation.notes.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.putrapebrianonurba.wordsy.core.components.CircledButton
import com.putrapebrianonurba.wordsy.core.components.TodoCard
import com.putrapebrianonurba.wordsy.core.util.formatTimeBasedTimeStamp
import com.putrapebrianonurba.wordsy.domain.model.ToDo

@Composable
fun ToDoSection(
    todos: List<ToDo>,
    onCheckedChange: (Int, Boolean) -> Unit,
    handleNavigateScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF67D4FC))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // Header
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "To-Do List",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = formatTimeBasedTimeStamp(System.currentTimeMillis(), "EEEE, dd MMMM yyyy"),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black.copy(alpha = 0.5f)
                )
            }

            // Navigation Button
            CircledButton(Icons.AutoMirrored.Default.ArrowBack, 140f, handleClick = handleNavigateScreen)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // List of To-Do items
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            todos.take(4).forEach { todo ->
                TodoCard(
                    todo = todo,
                    onCheckedChange = { isChecked ->
                        onCheckedChange(todo.id, isChecked)
                    }
                )
            }
        }
    }
}

