package ir.adicom.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ir.adicom.myapplication.navigation.AppNavHost
import ir.adicom.myapplication.navigation.BottomNavigationBar
import ir.adicom.myapplication.navigation.bottomNavItems
import ir.adicom.myapplication.ui.theme.Compose_uiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Compose_uiTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                val currentScreenRoute = backStackEntry.value?.destination?.route

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = bottomNavItems,
                            currentScreenRoute = currentScreenRoute,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    AppNavHost(
                        navHostController = navController,
                        modifier = Modifier.padding(bottom = it.calculateBottomPadding())
                    )
                }
            }
        }
    }
}
