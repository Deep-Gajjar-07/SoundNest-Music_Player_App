package com.example.soundnest.ui.screens.libraryscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.soundnest.R
import com.example.soundnest.ui.navigation.Routes
import com.example.soundnest.ui.theme.Secondary
import com.example.soundnest.ui.theme.TextWhite

@Composable
fun SongListItem(navController: NavController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Routes.Player)
            },
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

            Image(
                painter = painterResource(R.drawable.song_icon),
                contentDescription = "Song album image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {

                Text(
                    text = "Song name",
                    fontSize = 17.sp,
                    color = TextWhite,
                    fontWeight = FontWeight.Medium,
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = "Song artist name",
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic,
                    color = Secondary
                )

            }

            Text(
                text = "3:55",
                fontSize = 16.sp,
                color = TextWhite,
            )

        }

    }

}