package com.example.soundnest.ui.screens.libraryscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soundnest.ui.theme.LightBlack
import com.example.soundnest.ui.theme.TextWhite

@Preview(showBackground = true)
@Composable
fun SearchBar() {

    var searchSong by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        TextField(
            value = searchSong,
            onValueChange = { searchSong = it },
            placeholder = { Text("Search your library..") },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = LightBlack,
                focusedTextColor = Color.White,
                unfocusedPlaceholderColor = Color.Gray,
                unfocusedTextColor = Color.Gray,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search songs icon",
                    tint = TextWhite
                )
            }
        )

    }

}