package com.example.soundnest.ui.screens.playerscreen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.RepeatOne
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.soundnest.R
import com.example.soundnest.data.media.PlayerManager
import com.example.soundnest.data.media.RepeatMode
import com.example.soundnest.ui.screens.libraryscreen.formatDuration
import com.example.soundnest.ui.theme.LightBlack
import com.example.soundnest.ui.theme.Primary
import com.example.soundnest.ui.theme.Secondary
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerScreen(navController: NavController) {

    val currentSong by PlayerManager.currentSong
    val isPlaying by PlayerManager.isPlaying
    var currentPosition by remember { mutableLongStateOf(0L) }
    var duration by remember { mutableLongStateOf(0L) }
    val repeatMode by PlayerManager.repeatMode
    val isShuffledEnabled by PlayerManager.isShuffleEnabled

    // slider update after delay of 500ms to update the slider current position.
    LaunchedEffect(Unit) {
        while (true) {
            currentPosition = PlayerManager.getCurrentPosition()
            duration = PlayerManager.getDuration()
            delay(500)
        }
    }

    if (currentSong == null) {
        Text("No Song Selected!!")
        return
    }

    Scaffold(
        topBar = { PlayerAppTopBar(navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier
                    .padding(top = 7.dp)
                    .size(250.dp),
                shape = RoundedCornerShape(20.dp)
            ) {

                val albumBitmap = currentSong?.albumArtUri?.let {
                    BitmapFactory.decodeFile(it)
                }

                if (albumBitmap == null) {
                    Image(
                        painter = painterResource(R.drawable.song_icon),
                        contentDescription = "Default Song Album cover",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Image(
                        bitmap = albumBitmap.asImageBitmap(),
                        contentDescription = "Song Album cover",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Spacer(Modifier.height(40.dp))

            Text(
                text = currentSong?.title ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                fontSize = 20.sp,
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = currentSong?.artist ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                textAlign = TextAlign.Start,
                fontStyle = FontStyle.Italic,
                maxLines = 1,
                style = MaterialTheme.typography.bodyLarge,
                color = Secondary
            )

            Spacer(Modifier.height(15.dp))

            Slider(
                value = currentPosition.toFloat(),
                onValueChange = {
                    // slider thumb follow with the movement.
                    currentPosition = it.toLong()
                },
                onValueChangeFinished = {
                    // when thumb got release song jumps to here.
                    if (duration > 0) {
                        PlayerManager.seekTo(currentPosition)
                    }
                },
                valueRange = 0f..maxOf(duration.toFloat(), 1f),
                colors = SliderDefaults.colors(
                    thumbColor = LightBlack,
                    activeTrackColor = Color.LightGray,
                    inactiveTrackColor = LightBlack,
                ),
                thumb = {
                    Icon(
                        imageVector = Icons.Default.Circle,
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(20.dp)
                    )
                },
                track = {
                    SliderDefaults.Track(
                        sliderState = it,
                        modifier = Modifier.height(3.dp)
                    )
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = formatDuration(currentPosition),
                    fontSize = 17.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Secondary
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = formatDuration(duration),
                    fontSize = 17.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Secondary
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            // song player buttons.
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = { PlayerManager.toggleShuffle() },
                ) {
                    Icon(
                        imageVector = Icons.Default.Shuffle,
                        contentDescription = "song shuffle icon button",
                        tint = if (isShuffledEnabled) Color.White else Color.Gray
                    )
                }

                IconButton(
                    onClick = { PlayerManager.playPrevious() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Gray.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.SkipPrevious,
                        contentDescription = "song previous icon button",
                    )
                }

                IconButton(
                    onClick = {
                        if (isPlaying) {
                            PlayerManager.pauseSong()
                        } else {
                            PlayerManager.resumeSong()
                        }
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Primary,
                        contentColor = Color.DarkGray
                    ),
                    modifier = Modifier.size(65.dp)
                ) {
                    Icon(
                        imageVector =
                            if (isPlaying) {
                                Icons.Default.Pause
                            } else {
                                Icons.Default.PlayArrow
                            },
                        contentDescription = "song play/pause icon button",
                        modifier = Modifier.size(35.dp)
                    )
                }

                IconButton(
                    onClick = { PlayerManager.playNext() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Gray.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.SkipNext,
                        contentDescription = "song next icon button",
                    )
                }

                IconButton(
                    onClick = {
                        PlayerManager.changeRepeatMode()
                    },
                ) {
                    Icon(
                        imageVector = when (repeatMode) {
                            RepeatMode.OFF -> Icons.Default.Repeat
                            RepeatMode.ALL -> Icons.Default.Repeat
                            RepeatMode.ONE -> Icons.Default.RepeatOne
                        },
                        contentDescription = "song repeat icon button",
                        tint = if (repeatMode == RepeatMode.OFF) Color.Gray else Color.White
                    )
                }

            }

        }
    }

}