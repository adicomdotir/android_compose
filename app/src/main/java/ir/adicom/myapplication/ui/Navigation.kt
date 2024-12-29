package ir.adicom.myapplication.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.adicom.myapplication.ui.add_edit_category.AddEditCategoryScreen
import ir.adicom.myapplication.ui.categories.CategoriesScreen
import ir.adicom.myapplication.ui.mymodel.MyModelScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "categories") {
        composable("main") {
            MyModelScreen(
                navController = navController,
                modifier = Modifier.padding(16.dp)
            )
        }
        // TODO: Add more destinations
        composable("add-category") {
            AddEditCategoryScreen(
                Modifier.padding(16.dp),
                navController = navController
            )
        }
        composable("categories") {
            CategoriesScreen(
                Modifier.padding(16.dp),
                navController = navController
            )
        }
    }
}
