package com.example.soundnest.ui.screens.libraryscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.soundnest.data.media.PlayerManager
import com.example.soundnest.ui.components.AppBottomNavbar
import com.example.soundnest.ui.components.AppTopBar
import com.example.soundnest.ui.navigation.Routes

@Composable
fun LibraryScreen(navController: NavController) {

    val viewModel: LibraryViewModel = viewModel()
    val songs by viewModel.songs.collectAsState(initial = emptyList())

    // for show latest song list(queue) to PlayerManager(for next/previous)
    LaunchedEffect(songs) {
        PlayerManager.setQueue(songs)
    }

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

            if (songs.isEmpty()) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Icon(
                        imageVector = Icons.Default.Inbox,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(bottom = 5.dp)
                    )

                    Text(
                        text = "Library is Empty!",
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp,
                    )
                }

            } else {

                LazyColumn {

                    items(songs) { song ->

                        SongListItem(
                            song = song,
                            onClick = {
                                PlayerManager.playSong(song)
                                navController.navigate(Routes.Player)
                            }
                        )

                    }

                }

            }

        }

    }

}