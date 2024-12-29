package ir.adicom.myapplication.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.adicom.myapplication.data.CategoryRepository
import ir.adicom.myapplication.data.local.database.Category
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    val uiState: StateFlow<CategoriesUiState> = categoryRepository.categories
        .map<List<Category>, CategoriesUiState>(CategoriesUiState::Success)
        .catch { emit(CategoriesUiState.Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CategoriesUiState.Loading)

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            categoryRepository.delete(category)
        }
    }
}

sealed interface CategoriesUiState {
    object Loading : CategoriesUiState
    data class Error(val throwable: Throwable) : CategoriesUiState
    data class Success(val data: List<Category>) : CategoriesUiState
}
