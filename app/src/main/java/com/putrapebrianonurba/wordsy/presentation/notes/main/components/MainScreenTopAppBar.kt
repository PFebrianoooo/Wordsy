package com.putrapebrianonurba.wordsy.presentation.notes.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.putrapebrianonurba.wordsy.core.components.CompactOutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopAppBar(
    showSearchField: Boolean,
    searchTerm: String,
    toggleShowSearchFormHandler: () -> Unit,
    searchTermValueOnChange: (String) -> Unit

) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (!showSearchField) {
            Text(
                "Wordsy",
                fontSize = 34.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.weight(1f)
            )

            Icon(
                Icons.Default.Search,
                contentDescription = "ShowSearchForm",
                tint = Color.DarkGray,
                modifier = Modifier
                    .size(22.dp)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = toggleShowSearchFormHandler
                    )
            )

        } else {
            Icon(
                Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier
                    .size(22.dp)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource,
                        onClick = toggleShowSearchFormHandler
                    )
            )

            Spacer(Modifier.width(12.dp))

            CompactOutlinedTextField(
                value = searchTerm,
                onValueChange = searchTermValueOnChange,
                onSearchPressed = {  },
                placeholder = "Cari catatanmu",
                modifier = Modifier.weight(1f)
            )
        }
    }
}