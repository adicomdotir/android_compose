package ir.adicom.myapplication.ui.bookmark

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.adicom.myapplication.repository.BookmarkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    var uiState by mutableStateOf(BookmarkUiState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.users.collect { list ->
                withContext(Dispatchers.Main) {
                    uiState =
                        uiState.copy(
                            list = list ?: emptyList()
                        )
                }
            }
        }
    }

}