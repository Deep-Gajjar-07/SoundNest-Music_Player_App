package com.example.soundnest.ui.screens.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.soundnest.R
import com.example.soundnest.ui.navigation.Routes
import com.example.soundnest.viewmodel.UserProfileViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: UserProfileViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        delay(2000)

        viewModel.hasUser { hasUser ->

            if (hasUser) {
                navController.navigate(Routes.Library) {
                    popUpTo(Routes.Splash) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(Routes.Welcome) {
                    popUpTo(Routes.Splash) {
                        inclusive = true
                    }
                }
            }

        }

    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp, bottom = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(R.drawable.app_logo),
                contentDescription = "Application Logo",
                modifier = Modifier
                    .clip(
                        shape = CircleShape,
                    )
                    .size(150.dp),
                contentScale = ContentScale.Crop,
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = "SoundNest",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(Modifier.height(30.dp))

            LinearProgressIndicator(
                modifier = Modifier.width(130.dp)
            )

        }

    }

}