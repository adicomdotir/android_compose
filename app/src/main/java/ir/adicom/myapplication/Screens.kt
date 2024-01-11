package ir.adicom.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.adicom.myapplication.navigation.Destination

sealed class Screens(val route: String) {
    object HomeScreen : Screens("HomeScreen")
    object SecondScreen : Screens("SecondScreen")
}

@Composable
fun Xxx() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route,
        modifier = Modifier
    ) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screens.SecondScreen.route + "/{id1}" + "/{id2}") {
            val data1 = it.arguments?.getString("id1")!!
            val data2 = it.arguments?.getString("id2")!!
            SecondScreen(navController = navController, data1 = data1, data2 = data2)
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    var data1 by remember {
        mutableStateOf("")
    }
    var data2 by remember {
        mutableStateOf("")
    }


    Scaffold {
        Column {
            OutlinedTextField(
                value = data1,
                onValueChange = {
                    data1 = it
                })
            OutlinedTextField(
                value = data2,
                onValueChange = {
                    data2 = it
                })
            Button(onClick = {
                navController.navigate(
                    Screens.SecondScreen.route + "/${data1}" + "/${data2}"
                )
            }) {
                Text(text = "Done", fontSize = 20.sp)
            }
        }
    }

}

@Composable
fun SecondScreen(navController: NavController, data1: String, data2: String) {
    Scaffold {
        Column {
            Text(
                text = "$data1 & $data2 is ${data1.toInt()} * ${data2.toInt()}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}