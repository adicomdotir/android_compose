package ir.adicom.myapplication.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ir.adicom.myapplication.Constants.HOME_SCREEN
import ir.adicom.myapplication.Constants.MOVIES_DETAILS_SCREEN
import ir.adicom.myapplication.data.model.MovieItem

@Composable
@ExperimentalAnimationApi
fun Navigation(
    navController: NavHostController,
    modifier: Modifier,
    data: Any? = null
) {
    val activity = LocalContext.current
    NavHost(
        navController,
        startDestination = NavScreen.HomeScreen.route
    ) {
        composable(
            route = NavScreen.HomeScreen.route
        ) {
            if (data is List<*> && data.all { it is MovieItem }) {
//                HomeScreen(navController = navController,
//                    movies = data as List<MovieItem>,
//                    onItemClick = {
//                        Toast.makeText(activity, "$it", Toast.LENGTH_SHORT).show()
//                    }
//                )
            }
        }
    }
}

fun navigationTitle(navController: NavController): String {
    return ""
}

sealed class NavScreen(val route: String) {
    object HomeScreen : NavScreen(HOME_SCREEN)
    object DetailsScreen : NavScreen(MOVIES_DETAILS_SCREEN) {
        const val routeWithArgument: String = "MoviesDetails/{id}"
        const val argument0: String = "id"
    }
}