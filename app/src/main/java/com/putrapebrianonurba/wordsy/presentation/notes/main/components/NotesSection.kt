package com.putrapebrianonurba.wordsy.presentation.notes.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.putrapebrianonurba.wordsy.domain.model.Notes

@Composable
fun NotesSection(
    allNotes: List<Notes>,
    onNoteClick: (Int) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    FlowRow(
        modifier = Modifier
            .padding(14.dp)
            .fillMaxWidth(),
        maxItemsInEachRow = 2,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        allNotes.forEach { notes ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFEDEDED))
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = { onNoteClick(notes.id) }
                    )
                    .padding(16.dp)
            ) {
                Column {

                    Text(
                        text = notes.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1E1E1E),
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = notes.content,
                        fontSize = 14.sp,
                        color = Color(0xFF5F5F5F),
                        maxLines = 6,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}
