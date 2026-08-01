package com.example.soundnest.ui.screens.libraryscreen

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
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundnest.R
import com.example.soundnest.data.local.Song
import com.example.soundnest.ui.theme.Secondary
import com.example.soundnest.ui.theme.TextWhite

@Composable
fun SongListItem(song: Song, onClick: () -> Unit, onDelete: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // converting saved image path into Bitmap
            val albumBitmap = song.albumArtUri?.let { path ->
                BitmapFactory.decodeFile(path)
            }

            if (albumBitmap != null) {
                Image(
                    bitmap = albumBitmap.asImageBitmap(),
                    contentDescription = "Song album image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.song_icon),
                    contentDescription = "default song album image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .weight(1f),
            ) {

                Text(
                    text = song.title,
                    fontSize = 16.sp,
                    maxLines = 1,
                    color = TextWhite,
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = song.artist,
                    fontSize = 15.sp,
                    maxLines = 1,
                    fontStyle = FontStyle.Italic,
                    color = Secondary
                )

            }

            Text(
                text = formatDuration(song.duration),
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                color = Secondary,
            )

            IconButton(
                onClick = { onDelete() },
            ) {
                Icon(
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = "Remove song from playlist",
                    tint = Color.Gray,
                    modifier = Modifier.size(22.dp)
                )
            }

        }

    }

    HorizontalDivider(color = Color.DarkGray)

}

// function for format song duration from milliseconds.
fun formatDuration(duration: Long): String {
    val minutes = duration / 1000 / 60
    val seconds = (duration / 1000) % 60

    return "%d:%02d".format(minutes, seconds)
}
