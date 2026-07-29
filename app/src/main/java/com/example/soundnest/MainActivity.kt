package com.example.soundnest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.soundnest.data.media.PlayerManager
import com.example.soundnest.ui.navigation.AppNavGraph
import com.example.soundnest.ui.theme.SoundNestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initializing player when the app starts.
        PlayerManager.initialize(applicationContext)

        enableEdgeToEdge()
        setContent {
            SoundNestTheme {
                AppNavGraph()
            }
        }
    }

    // on app close the player resources also got release.
    override fun onDestroy() {
        super.onDestroy()
        PlayerManager.releasePlayer()
    }

}