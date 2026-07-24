package com.example.soundnest.ui.screens.profilescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soundnest.ui.theme.LightBlack

@Composable
fun SettingItems(
    icon: ImageVector,
    name: String,
    iconContainerColor: Color = Color.Gray.copy(alpha = 0.3f),
    iconColor: Color = Color.LightGray,
    onClick: () -> Unit,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightBlack
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    )
                    .background(
                        color = iconContainerColor
                    ),
                contentAlignment = Alignment.Center,
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = "Setting Item Icons",
                    tint = iconColor,
                )
            }

            Spacer(Modifier.width(16.dp))

            Text(
                text = name,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
            )

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color.LightGray
            )


        }

    }

}