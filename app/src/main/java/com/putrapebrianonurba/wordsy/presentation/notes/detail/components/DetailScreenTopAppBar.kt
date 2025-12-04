package com.putrapebrianonurba.wordsy.presentation.notes.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreenTopAppBar(
    navController: NavController,
    noteIsEdited: Boolean,
    showDialoghandler: () -> Unit,
    updateNoteHandler: () -> Unit

) {
    val interactionSource = remember { MutableInteractionSource() }
    val currentKeyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = "Back",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(22.dp)
                .clickable(
                    indication = null,
                    interactionSource = interactionSource,
                    onClick = { navController.popBackStack() }
                )
        )

        Spacer(modifier = Modifier.weight(1f))

        if (!noteIsEdited) {
            Icon(
                Icons.Outlined.Delete,
                contentDescription = "delete",
                tint = Color.DarkGray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = showDialoghandler
                    )
            )
        } else {
            Icon(
                Icons.Default.Check,
                contentDescription = "save",
                tint = Color.DarkGray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = {
                            updateNoteHandler()
                            focusManager.clearFocus()
                            currentKeyboard?.hide()
                        }
                    )
            )
        }
    }
}