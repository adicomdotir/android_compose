package ir.adicom.myapplication.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import ir.adicom.myapplication.data.model.MovieItem
import ir.adicom.myapplication.ui.components.AppBar
import ir.adicom.myapplication.ui.navigation.Navigation
import ir.adicom.myapplication.ui.navigation.navigationTitle

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val navController = rememberAnimatedNavController()
    val movies: List<MovieItem> by viewModel.movies.collectAsState(initial = listOf())
    val isLoading: Boolean by viewModel.isLoading

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        topBar = { AppBar(title = navigationTitle(navController)) },
        modifier = Modifier
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Navigation(navController, modifier, movies)
    }
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
        )
    }
}