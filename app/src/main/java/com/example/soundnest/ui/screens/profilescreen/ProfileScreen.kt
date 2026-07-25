package com.example.soundnest.ui.screens.profilescreen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.soundnest.ui.components.AppBottomNavbar
import com.example.soundnest.ui.components.AppTopBar
import com.example.soundnest.ui.theme.LightBlack
import com.example.soundnest.ui.theme.Secondary

@Composable
fun ProfileScreen(navController: NavController) {

    var showEditProfileDialog by remember { mutableStateOf(false) }

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
                text = "John Doe",
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
                            text = "2345",
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

                SettingItems(
                    icon = Icons.Default.Download,
                    name = "Import Songs",
                    onClick = {}
                )

                Spacer(Modifier.height(25.dp))

                SettingItems(
                    icon = Icons.Default.ExitToApp,
                    name = "Leave Profile",
                    iconContainerColor = Color.Red.copy(alpha = 0.5f),
                    iconColor = Color.White.copy(alpha = 0.8f),
                    onClick = {}
                )

            }

        }

    }

    if (showEditProfileDialog) {
        EditProfileBottomDialog { showEditProfileDialog = false }
    }

}