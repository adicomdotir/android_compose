package ir.adicom.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ir.adicom.myapplication.ui.bookmark.BookmarkScreen
import ir.adicom.myapplication.ui.details.DetailsScreen
import ir.adicom.myapplication.ui.users.UsersScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.USER
    ) {
        composable(Route.USER) { backStackEntry ->
            UsersScreen(
                onUserClick = { username ->
                    // In order to discard duplicated navigation events, we check the Lifecycle
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Route.DETAIL}/$username")
                    }
                },
                onBookmarkClick = {
                    navController.navigate(Route.BOOKMARK)
                }
            )
        }
        composable(
            route = "${Route.DETAIL}/{${Argument.USERNAME}}",
            arguments = listOf(
                navArgument(Argument.USERNAME) {
                    type = NavType.StringType
                }
            ),
        ) {
            DetailsScreen()
        }
        composable(Route.BOOKMARK) { backStackEntry ->
            BookmarkScreen(
                onUserClick = { username ->
                    // In order to discard duplicated navigation events, we check the Lifecycle
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Route.DETAIL}/$username")
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

object Route {
    const val USER = "user"
    const val DETAIL = "detail"
    const val BOOKMARK = "bookmark"
}

object Argument {
    const val USERNAME = "username"
}