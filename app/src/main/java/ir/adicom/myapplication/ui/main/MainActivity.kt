package ir.adicom.myapplication.ui

import NavGraph
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import ir.adicom.myapplication.presentation.home.HomeViewModel
import ir.adicom.myapplication.presentation.search.SearchCityViewModel
import ir.adicom.myapplication.ui.theme.ComposeWeatherAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val searchCityViewModel: SearchCityViewModel by viewModels()
    private lateinit var pl: ActivityResultLauncher<Array<String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            flowOf(4, 2, 1, 3).collect {
                Timber.e("flowOf: $it")
            }

            (1 .. 5).asFlow().collect {
                Timber.e("asFlow: $it")
            }

            flow {
                for (i in 1..5) {
                    emit(i)
                }
            }.collect {
                Timber.e("flow: $it")
            }

            channelFlow {
                for (i in 1..5) {
                    send(i)
                }
            }.collect {
                Timber.e("channelFlow: $it")
            }
        }

        setContent {
            ComposeWeatherAppTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(color = Color.Transparent)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavGraph(
                        homeViewModel = homeViewModel,
                        searchCityViewModel = searchCityViewModel
                    )
                }
            }
        }
    }
}