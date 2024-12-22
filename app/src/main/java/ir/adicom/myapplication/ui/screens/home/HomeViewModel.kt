package ir.adicom.myapplication.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ir.adicom.myapplication.deprecated.data.model.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeViewModel: ViewModel() {
    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    val movies: Flow<List<MovieItem>> = flow {  }
}
