package com.example.soundnest.ui.screens.welcomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.soundnest.R
import com.example.soundnest.ui.navigation.Routes
import com.example.soundnest.ui.theme.Primary
import com.example.soundnest.ui.theme.Secondary

@Preview(showSystemUi = true)
@Composable
fun WelcomeScreen(navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            Image(
                painter = painterResource(R.drawable.song_icon),
                contentDescription = "Music Icon Image",
                modifier = Modifier
                    .size(240.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(24.dp)),
            )

            Spacer(Modifier.height(30.dp))

            Text(
                text = "Welcome to SoundNest",
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
            )

            Text(
                text = "Import your favorite songs from your device and enjoy your personal music library anytime.",
                fontSize = 17.sp,
                color = Secondary,
                modifier = Modifier.padding(top = 15.dp),
            )

            Spacer(Modifier.height(70.dp))

            Button(
                onClick = {
                    navController.navigate(Routes.CreateProfile) {
                        popUpTo(Routes.Welcome) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = Color.Black
                ),
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 20.sp,
                )

                Spacer(Modifier.width(12.dp))

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowRightAlt,
                    contentDescription = "Get Started arrow icon"
                )
            }

        }

    }

}