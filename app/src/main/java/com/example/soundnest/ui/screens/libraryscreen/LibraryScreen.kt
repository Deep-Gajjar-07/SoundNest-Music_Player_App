package com.example.soundnest.ui.screens.libraryscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.soundnest.ui.components.AppBottomNavbar
import com.example.soundnest.ui.components.AppTopBar

@Preview
@Composable
fun LibraryScreen(navController: NavController) {

    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomNavbar(navController, routeName = "Library") },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(innerPadding)
        ) {

            // Searchbar for searching songs by name & author.
            SearchBar()

            Spacer(Modifier.height(12.dp))

            for (i in 1..3) {
                SongListItem(navController)
            }

        }

    }

}