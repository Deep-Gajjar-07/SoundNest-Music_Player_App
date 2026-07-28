package com.example.soundnest.ui.screens.libraryscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.soundnest.ui.components.AppBottomNavbar
import com.example.soundnest.ui.components.AppTopBar

@Composable
fun LibraryScreen(navController: NavController) {

    val viewModel: LibraryViewModel = viewModel()
    val songs by viewModel.songs.collectAsState(initial = emptyList())

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

            LazyColumn {

                items(songs) { song ->

                    SongListItem(
                        song = song,
                        navController = navController
                    )

                }

            }

        }

    }

}