package com.example.soundnest.ui.screens.profilescreen

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.soundnest.data.local.Song
import com.example.soundnest.data.media.SongMetaDataReader
import com.example.soundnest.ui.components.AppAlertDialogBox
import com.example.soundnest.ui.components.AppBottomNavbar
import com.example.soundnest.ui.components.AppTopBar
import com.example.soundnest.ui.navigation.Routes
import com.example.soundnest.ui.screens.createprofilescreen.SongViewModel
import com.example.soundnest.ui.screens.createprofilescreen.UserProfileViewModel
import com.example.soundnest.ui.theme.LightBlack
import com.example.soundnest.ui.theme.Secondary

@Composable
fun ProfileScreen(
    navController: NavController,
    userViewModel: UserProfileViewModel = viewModel(),
    songViewModel: SongViewModel = viewModel()
) {

    var showEditProfileDialog by remember { mutableStateOf(false) }
    var showAlertDialogBox by remember { mutableStateOf(false) }
    val username by userViewModel.username.collectAsState(initial = null)
    val totalSongs by songViewModel.totalSongs.collectAsState(initial = 0)

    val context = LocalContext.current

    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomNavbar(navController = navController, routeName = "Profile") },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 20.dp, start = 16.dp, end = 16.dp)
        ) {

            Text(
                text = username?.name ?: "Guest",
                fontSize = 26.sp,
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LightBlack
                ),
                shape = RoundedCornerShape(8.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "LIBRARY SUMMERY",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Secondary,
                    )

                    Spacer(Modifier.height(15.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        Text(
                            text = "$totalSongs",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = "Songs",
                            fontSize = 16.sp,
                            color = Secondary,
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Account Settings",
                fontSize = 16.sp,
            )

            Spacer(Modifier.height(20.dp))

            Column {

                SettingItems(
                    icon = Icons.Default.PersonOutline,
                    name = "Edit Profile",
                    onClick = { showEditProfileDialog = true }
                )

                // launcher used to open Android File Picker.
                val importSongsLauncher = rememberLauncherForActivityResult(

                    // contract for what kind of picker want to open.
                    // open file picker and user can select multiple files.
                    contract = ActivityResultContracts.OpenMultipleDocuments()

                ) { uris ->
                    // this block will be run after user selects files.

                    if (uris.isNotEmpty()) {
                        val songList = mutableListOf<Song>()
                        uris.forEach { uri ->

                            context.contentResolver.takePersistableUriPermission(
                                uri,
                                // read permission only because of just playing audio(songs)
                                Intent.FLAG_GRANT_READ_URI_PERMISSION
                            )

                            // reading metadata from selected song(audio).
                            val song = SongMetaDataReader.getSongMetadata(context, uri)

                            songList.add(song)
                        }
                        // inserting all selected songs(audio) to DB.
                        songViewModel.insertSongs(songList)
                        Toast.makeText(context, "Song Imported!", Toast.LENGTH_SHORT).show()
                    }

                }

                SettingItems(
                    icon = Icons.Default.Download,
                    name = "Import Songs",
                    onClick = {
                        // open file picker on click.
                        importSongsLauncher.launch(
                            // shows only audio files
                            arrayOf("audio/*")
                        )
                    }
                )

                Spacer(Modifier.height(25.dp))

                SettingItems(
                    icon = Icons.Default.ExitToApp,
                    name = "Delete Profile",
                    iconContainerColor = Color.Red.copy(alpha = 0.5f),
                    iconColor = Color.White.copy(alpha = 0.8f),
                    onClick = { showAlertDialogBox = true }
                )

            }

        }

    }

    if (showEditProfileDialog) {
        EditProfileBottomDialog(
            user = username!!,
            onCancel = { showEditProfileDialog = false },
            onUpdate = { newName ->
                userViewModel.updateUser(
                    userProfile = username!!.copy(
                        name = newName
                    )
                )
                Toast.makeText(context, "Profile Updated Successfully!", Toast.LENGTH_SHORT).show()
                showEditProfileDialog = false
            }
        )
    }

    if (showAlertDialogBox) {
        AppAlertDialogBox(
            title = "Delete Profile?",
            message = "This will permanently remove your profile and all imported songs from SoundNest." +
                    " Your original music files on your device will not be deleted!",
            btnText = "Yes, Delete",
            onDismiss = { showAlertDialogBox = false },
            onDelete = {
                username?.let {
                    songViewModel.deleteSongs()
                    userViewModel.deleteUser(it)
                }
                showAlertDialogBox = false

                Toast.makeText(context, "Profile Deleted Successfully!", Toast.LENGTH_SHORT).show()

                navController.navigate(Routes.Welcome) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        )
    }

}