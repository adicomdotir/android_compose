package ir.adicom.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.adicom.myapplication.ui.theme.Compose_uiTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Compose_uiTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    Scaffold(
        topBar = { TopBar() },
        backgroundColor = colorResource(id = R.color.purple_700)
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {

        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(text = "My App", fontSize = 18.sp)
        },
        backgroundColor = colorResource(id = R.color.purple_500),
        contentColor = Color.White
    )
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController)
        }
        composable(
            "details/{countryName}",
            arguments = listOf(navArgument("countryName") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("countryName")?.let { countryName ->
                DetailsScreen(countryName = countryName)
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchView(textState)
        CountryList(navController = navController, state = textState)
    }
}