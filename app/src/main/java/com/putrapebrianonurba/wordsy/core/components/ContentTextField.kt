package com.putrapebrianonurba.wordsy.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContentTextField(contentState: String, onValueChange: (String) -> Unit) {
    BasicTextField(
        value = contentState,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp
        ),
        cursorBrush = SolidColor(Color.Black),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default),
        keyboardActions = KeyboardActions(onAny = { onValueChange(contentState + "\n")}),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                if (contentState.isEmpty()) {
                    Text(
                        text = "Tulis sesuatu disini",
                        color = Color.Gray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 24.sp
                    )
                }
                innerTextField()
            }
        }
    )
}