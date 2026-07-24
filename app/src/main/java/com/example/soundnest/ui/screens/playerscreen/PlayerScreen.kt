package com.example.soundnest.ui.screens.playerscreen

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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundnest.R
import com.example.soundnest.ui.theme.LightBlack
import com.example.soundnest.ui.theme.Primary
import com.example.soundnest.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PlayerScreen() {

    var progress by remember { mutableFloatStateOf(0.0f) }

    Scaffold(
        topBar = { PlayerAppTopBar() },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier.size(260.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.song_icon),
                    contentDescription = "Song Album cover",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(Modifier.height(40.dp))

            Text(
                text = "Song Name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.headlineSmall,
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Song Artist name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge,
                color = Secondary
            )

            Spacer(Modifier.height(20.dp))

            Slider(
                value = progress,
                onValueChange = { progress = it },
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
                    text = "01:12",
                    fontSize = 17.sp,
                    color = Secondary
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "03:24",
                    fontSize = 17.sp,
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
                    onClick = {},
                ) {
                    Icon(
                        imageVector = Icons.Default.Shuffle,
                        contentDescription = "song shuffle icon button",
                    )
                }

                IconButton(
                    onClick = {},
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
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Primary,
                        contentColor = Color.DarkGray
                    ),
                    modifier = Modifier.size(65.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "song play icon button",
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(
                    onClick = {},
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
                    onClick = {},
                ) {
                    Icon(
                        imageVector = Icons.Default.Repeat,
                        contentDescription = "song repeat icon button",
                    )
                }

            }

        }
    }

}