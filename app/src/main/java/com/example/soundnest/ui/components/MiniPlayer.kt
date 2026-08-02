package com.example.soundnest.ui.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.soundnest.R
import com.example.soundnest.data.media.PlayerManager
import com.example.soundnest.ui.navigation.Routes
import com.example.soundnest.ui.theme.Secondary
import com.example.soundnest.ui.theme.TextWhite

@Composable
fun MiniPlayer(navController: NavController) {

    val currentSong by PlayerManager.currentSong
    val isPlaying by PlayerManager.isPlaying

    if (currentSong == null) return

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate(Routes.Player) },
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray.copy(alpha = 0.56f)
        ),
        shape = RoundedCornerShape(0.dp)
    ) {

        Row(
            modifier = Modifier.padding(start = 7.dp, end = 7.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val albumBitmap = currentSong!!.albumArtUri?.let { path ->
                BitmapFactory.decodeFile(path)
            }

            if (albumBitmap != null) {
                Image(
                    bitmap = albumBitmap.asImageBitmap(),
                    contentDescription = "Song album image",
                    modifier = Modifier
                        .size(45.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.song_icon),
                    contentDescription = "Default song album image",
                    modifier = Modifier
                        .size(45.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f)
            ) {

                Text(
                    text = currentSong!!.title,
                    fontSize = 16.sp,
                    maxLines = 1,
                    color = TextWhite,
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = currentSong!!.artist,
                    fontSize = 15.sp,
                    maxLines = 1,
                    fontStyle = FontStyle.Italic,
                    color = Secondary
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                IconButton(
                    onClick = { PlayerManager.playPrevious() }
                ) {
                    Icon(
                        imageVector = Icons.Default.SkipPrevious,
                        contentDescription = "play previous song icon button",
                        modifier = Modifier.size(25.dp),
                        tint = Secondary
                    )
                }

                IconButton(
                    onClick = {
                        if (isPlaying) {
                            PlayerManager.pauseSong()
                        } else {
                            PlayerManager.resumeSong()
                        }
                    }
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
                    onClick = { PlayerManager.playNext() }
                ) {
                    Icon(
                        imageVector = Icons.Default.SkipNext,
                        contentDescription = "play next song icon button",
                        modifier = Modifier.size(25.dp),
                        tint = Secondary
                    )
                }

            }

        }

    }
}