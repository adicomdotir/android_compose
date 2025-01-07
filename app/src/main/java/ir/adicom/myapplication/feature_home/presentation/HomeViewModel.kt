package ir.adicom.myapplication.feature_home.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.adicom.myapplication.Routes
import ir.adicom.myapplication.core.domain.models.NoteModel
import ir.adicom.myapplication.feature_home.domain.GetNotesUseCase
import ir.adicom.myapplication.feature_home.domain.ListenNotesUseCase
import ir.adicom.myapplication.feature_home.domain.NotesEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val listenNotesUseCase: ListenNotesUseCase
) : ViewModel() {

    private val TAG = "HomeViewModel"
    val notesList = mutableStateListOf<NoteModel>()
    private val _eventFlow = MutableSharedFlow<HomeEvent>()
    val eventFlow: SharedFlow<HomeEvent> = _eventFlow.asSharedFlow()
    private val _scope = viewModelScope

    init {
        _scope.launch(Dispatchers.IO) {
            val items = getNotesUseCase.execute()
            delay(500)
            notesList.addAll(items)
        }

        _scope.launch {
            listenNotesUseCase.execute().collect { event ->
                when (event) {
                    is NotesEvent.Insert -> notesList.add(0, event.value)

                    is NotesEvent.Update -> {
                        val itemIndex = notesList.indexOfFirst { it.id == event.value.id }

                        if (itemIndex != -1) {
                            notesList[itemIndex] = event.value
                        }
                    }

                    is NotesEvent.Delete -> {
                        val itemIndex = notesList.indexOfFirst { it.id == event.value }

                        if (itemIndex != -1) {
                            notesList.removeAt(itemIndex)
                        }
                    }
                }
            }
        }
    }

    fun action(action: HomeAction) {
        when (action) {
            HomeAction.AddNewNote -> addNewNote()
            is HomeAction.ListItemOnClick -> listItemOnClick(action.value)
        }
    }

    private fun addNewNote() = _scope.launch {
        val route = Routes.ADD_NOTE + "/-1"
        _eventFlow.emit(HomeEvent.NavigateNext(route))
    }

    private fun listItemOnClick(id: Int) = _scope.launch(Dispatchers.Main) {
        Timber.tag(TAG).d("listItemOnClick: $id")
        val route = Routes.ADD_NOTE + "/$id"
        _eventFlow.emit(HomeEvent.NavigateNext(route))
    }
}
