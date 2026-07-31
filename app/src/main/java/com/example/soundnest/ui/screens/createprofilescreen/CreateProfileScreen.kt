package com.example.soundnest.ui.screens.createprofilescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.soundnest.data.local.UserProfile
import com.example.soundnest.ui.components.AppTopBar
import com.example.soundnest.ui.navigation.Routes
import com.example.soundnest.ui.theme.LightBlack
import com.example.soundnest.ui.theme.Primary
import com.example.soundnest.ui.theme.Secondary
import com.example.soundnest.ui.theme.TextWhite
import com.example.soundnest.viewmodel.UserProfileViewModel

@Composable
fun CreateProfile(
    navController: NavController,
    userProfileViewModel: UserProfileViewModel = viewModel()
) {

    var name by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppTopBar() },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
        ) {

            Text(
                text = "Create Profile",
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
            )

            Text(
                text = "Enter your name to set up your personal music experience!",
                fontSize = 16.sp,
                color = Secondary,
                modifier = Modifier.padding(top = 5.dp)
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Display Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                maxLines = 1,
                shape = RoundedCornerShape(8.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile person icon",
                        tint = Color.LightGray,
                        modifier = Modifier.size(20.dp)
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = LightBlack,
                    focusedContainerColor = LightBlack,
                    unfocusedLabelColor = Color.LightGray,
                    focusedLabelColor = Color.LightGray,
                    unfocusedTextColor = TextWhite,
                    focusedTextColor = TextWhite,
                )
            )

            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {
                    // Inserting username to table UserProfile
                    userProfileViewModel.insertUser(
                        userProfile = UserProfile(name = name)
                    )

                    navController.navigate(Routes.Library) {
                        popUpTo(Routes.CreateProfile) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary, contentColor = Color.DarkGray
                ),
                enabled = name.isNotBlank()
            ) {
                Text(
                    text = "Create Profile",
                    fontSize = 18.sp,
                )

            }


        }

    }

}