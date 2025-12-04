package com.putrapebrianonurba.wordsy.presentation.todo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.putrapebrianonurba.wordsy.core.components.CompactOutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoBottomSheet(
    show: Boolean,
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    onDelete: () -> Unit,
    onSave: () -> Unit,
    onDismiss: () -> Unit
) {
    if (!show) return

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = Color(0xFFEDEDED),
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .width(45.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.DarkGray.copy(alpha = 0.3f))
            )
        },
        scrimColor = Color(0xFFEDEDED).copy(alpha = 0.5f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            val interactionSource = remember { MutableInteractionSource() }

            CompactOutlinedTextField(
                value = textFieldValue,
                onValueChange = onValueChange,
                onSearchPressed = { },
                placeholder = "Misalkan: Belajar Kotlin",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Delete",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = onDelete
                    )
                )

                Text(
                    text = "Save",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = onSave
                    )
                )
            }
        }
    }
}
