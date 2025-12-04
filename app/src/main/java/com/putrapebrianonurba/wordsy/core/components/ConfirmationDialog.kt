package com.putrapebrianonurba.wordsy.core.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmationDialog(handleOnDismiss: () -> Unit, handleConfirmButton: () -> Unit, handleDismissButton: () -> Unit) {
    AlertDialog(
        onDismissRequest = handleOnDismiss,
        confirmButton = {
            TextButton(
                onClick = handleConfirmButton,
            ) {
                Text(
                    "Hapus",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }
        },
        title = {
            Text(
                "Menghapus",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.Black
            )
        },
        text = {
            Text(
                "Apakah kamu yakin untuk menghapus catatan ini?",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.Gray
            )
        },
        dismissButton = {
            TextButton(
                onClick = handleDismissButton
            ) {
                Text(
                    "Batal",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    )
}