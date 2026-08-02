package com.example.soundnest.ui.screens.libraryscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.soundnest.ui.theme.LightBlack

@Composable
fun SearchBar(
    searchText: String,
    onSearchChange: (String) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        TextField(
            value = searchText,
            onValueChange = onSearchChange,
            placeholder = { Text("Search your library..") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = LightBlack,
                focusedTextColor = Color.White,
                unfocusedPlaceholderColor = Color.LightGray,
                unfocusedTextColor = Color.Gray,
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search songs icon",
                    tint = Color.LightGray,
                    modifier = Modifier.size(25.dp)
                )
            },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(
                        onClick = { onSearchChange("") }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Cancel,
                            contentDescription = "Clear Search Icon Button",
                            tint = Color.Gray,
                        )

                    }
                }
            }
        )

    }

}