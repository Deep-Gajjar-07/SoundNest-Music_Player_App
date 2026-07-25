package com.example.soundnest.ui.screens.profilescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
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
import com.example.soundnest.ui.theme.LightBlack
import com.example.soundnest.ui.theme.Primary
import com.example.soundnest.ui.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileBottomDialog(
    onCancel: () -> Unit
) {

    var updatedDisplayName by remember { mutableStateOf("John Doe") }

    ModalBottomSheet(
        onDismissRequest = onCancel,
        containerColor = LightBlack,
        modifier = Modifier.navigationBarsPadding(),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {

            Text(
                text = "Edit Profile",
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextWhite
            )

            Spacer(Modifier.height(25.dp))

            TextField(
                value = updatedDisplayName,
                onValueChange = { updatedDisplayName = it },
                label = { Text(text = "Display Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                shape = RoundedCornerShape(8.dp),
                maxLines = 1,
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
                    unfocusedContainerColor = Color.DarkGray,
                    focusedContainerColor = Color.DarkGray,
                    unfocusedLabelColor = Color.LightGray,
                    focusedLabelColor = Color.LightGray,
                    unfocusedTextColor = TextWhite,
                    focusedTextColor = TextWhite,
                )
            )

            Spacer(Modifier.height(35.dp))

            Button(
                onClick = onCancel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary, contentColor = Color.DarkGray
                ),
            ) {
                Text(
                    text = "Update Profile",
                    fontSize = 18.sp,
                )

            }

        }

    }

}