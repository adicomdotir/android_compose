package ir.adicom.myapplication.ui.random_number

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.adicom.myapplication.data.RandomNumberRepository
import ir.adicom.myapplication.ui.random_number.RandomNumberUiState.Error
import ir.adicom.myapplication.ui.random_number.RandomNumberUiState.Loading
import ir.adicom.myapplication.ui.random_number.RandomNumberUiState.Success
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RandomNumberViewModel @Inject constructor(
    private val randomNumberRepository: RandomNumberRepository
) : ViewModel() {

    val uiState: StateFlow<RandomNumberUiState> = randomNumberRepository
        .randomNumbers.map<List<Int>, RandomNumberUiState>(::Success)
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    val uiCountState: StateFlow<Int> = randomNumberRepository.randomNumbersCount.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun addRandomNumber(value: Int) {
        viewModelScope.launch {
            randomNumberRepository.add((Random.nextDouble() * 100).toInt())
        }
    }
}

sealed interface RandomNumberUiState {
    object Loading : RandomNumberUiState
    data class Error(val throwable: Throwable) : RandomNumberUiState
    data class Success(val data: List<Int>) : RandomNumberUiState
}
