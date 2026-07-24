package com.example.soundnest.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.soundnest.ui.navigation.Routes
import com.example.soundnest.ui.theme.LightBlack
import com.example.soundnest.ui.theme.NavigationItemBGColor
import com.example.soundnest.ui.theme.Primary
import com.example.soundnest.ui.theme.Secondary

@Preview
@Composable
fun AppBottomNavbar(navController: NavController, routeName: String) {

    val navItems = listOf(
        NavItems(Icons.Default.LibraryMusic, "Library", Routes.Library),
        NavItems(Icons.Default.Person, "Profile", Routes.Profile)
    )

    NavigationBar(
        containerColor = LightBlack
    ) {

        navItems.forEach { items ->
            NavigationBarItem(
                onClick = {
                    navController.navigate(items.routes) {
                        popUpTo(Routes.Library) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = routeName == items.name,
                modifier = Modifier.size(70.dp),
                icon = {
                    Icon(
                        imageVector = items.icon,
                        contentDescription = "Navigation icons",
                        modifier = Modifier.size(28.dp)
                    )
                },
                label = { Text(text = items.name, fontSize = 14.sp) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = NavigationItemBGColor,
                    selectedTextColor = Primary,
                    unselectedTextColor = Secondary,
                    selectedIconColor = Primary,
                    unselectedIconColor = Secondary
                ),
            )
        }

    }

}

data class NavItems(
    val icon: ImageVector,
    val name: String,
    val routes: Routes
)