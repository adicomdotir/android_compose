//package ir.adicom.myapplication.deprecated.ui.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavType
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import ir.adicom.myapplication.deprecated.Constants.DETAIL_SCREEN
//import ir.adicom.myapplication.deprecated.Constants.MAIN_SCREEN
//import ir.adicom.myapplication.DetailScreen
//import ir.adicom.myapplication.MainScreen
//
//@Composable
//fun NavigationStack() {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = NavScreen.MainScreen.route) {
//        composable(route = NavScreen.MainScreen.route) {
//            MainScreen(navController = navController)
//        }
//        composable(
//            route = NavScreen.DetailsScreen.route + "?text={text}",
//            arguments = listOf(
//                navArgument("text") {
//                    type = NavType.StringType
//                    nullable = true
//                }
//            )
//        ) {
//            DetailScreen(text = it.arguments?.getString("text"))
//        }
//    }
//}
//
//sealed class NavScreen(val route: String) {
//    object MainScreen : NavScreen(MAIN_SCREEN)
//    object DetailsScreen : NavScreen(DETAIL_SCREEN)
//}
