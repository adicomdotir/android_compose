package ir.adicom.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ir.adicom.myapplication.ui.screens.home.HomeScreen
import ir.adicom.myapplication.ui.screens.theme.ComposeWeatherAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeWeatherAppTheme {
                HomeScreen()
            }
        }
    }
}
