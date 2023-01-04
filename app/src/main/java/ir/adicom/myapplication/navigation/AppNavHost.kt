package ir.adicom.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ir.adicom.list.ListScreen
import ir.adicom.profile.ProfileScreen

@Composable
fun AppNavHost(navHostController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = Destination.HomeScreen.route,
        modifier = modifier
    ) {
        composable(Destination.HomeScreen.route) {
            ListScreen()
        }
        composable(Destination.ProfileScreen.route) {
            ProfileScreen()
        }
    }
}