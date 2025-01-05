package ir.adicom.myapplication.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.adicom.myapplication.Routes
import ir.adicom.myapplication.models.NoteModel
import ir.adicom.myapplication.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel : ViewModel() {
    private val TAG = "HomeViewModel"

    private val repository: NotesRepository = NotesRepository.getInstance()

    val notesList = mutableStateListOf<NoteModel>()
    private val _eventFlow = MutableSharedFlow<HomeEvent>()
    val eventFlow: SharedFlow<HomeEvent> = _eventFlow.asSharedFlow()
    private val _scope = viewModelScope

    init {
        _scope.launch(Dispatchers.IO) {
            val items = repository.getAll()
            delay(500)
            notesList.addAll(items)
        }

        _scope.launch {
            repository.newNoteInsertionListener.collect { newNote ->
                notesList.add(0, newNote)
            }
        }

        _scope.launch {
            repository.updateNoteListener.collect { updateNote ->
                val itemIndex = notesList.indexOfFirst { it.id == updateNote.id }

                if (itemIndex != -1) {
                    notesList[itemIndex] = updateNote
                }

            }
        }

        _scope.launch {
            repository.deleteNoteListener.collect { id ->
                val itemIndex = notesList.indexOfFirst { it.id == id }

                if (itemIndex != -1) {
                    notesList.removeAt(itemIndex)
                }

            }
        }
    }

    fun listItemOnClick(id: Int) = _scope.launch(Dispatchers.Main) {
        Timber.tag(TAG).d("listItemOnClick: $id")
        val route = Routes.ADD_NOTE + "/$id"
        _eventFlow.emit(HomeEvent.NavigateNext(route))
    }

    sealed class HomeEvent {
        data class NavigateNext(val route: String) : HomeEvent()
    }
}
