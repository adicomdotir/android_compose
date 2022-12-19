package ir.adicom.myapplication.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(
    val route: String,
    val icon: ImageVector,
) {
    object ProfileScreen : Destination("profile", Icons.Default.Person)
    object HomeScreen : Destination("home", Icons.Default.Home)
}

val bottomNavItems = listOf(
    Destination.HomeScreen,
    Destination.ProfileScreen
)