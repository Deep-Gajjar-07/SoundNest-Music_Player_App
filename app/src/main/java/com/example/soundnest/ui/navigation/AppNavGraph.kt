package com.example.soundnest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.soundnest.ui.screens.createprofilescreen.CreateProfile
import com.example.soundnest.ui.screens.libraryscreen.LibraryScreen
import com.example.soundnest.ui.screens.playerscreen.PlayerScreen
import com.example.soundnest.ui.screens.profilescreen.ProfileScreen
import com.example.soundnest.ui.screens.splashscreen.SplashScreen
import com.example.soundnest.ui.screens.welcomescreen.WelcomeScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {

        composable<Routes.Splash> {
            SplashScreen(navController)
        }

        composable<Routes.Welcome> {
            WelcomeScreen(navController)
        }

        composable<Routes.CreateProfile> {
            CreateProfile(navController)
        }

        composable<Routes.Library> {
            LibraryScreen(navController)
        }

        composable<Routes.Player> {
            PlayerScreen(navController)
        }

        composable<Routes.Profile> {
            ProfileScreen(navController)
        }

    }

}