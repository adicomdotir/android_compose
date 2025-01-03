package ir.adicom.myapplication.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.adicom.myapplication.Routes
import ir.adicom.myapplication.models.NoteModel
import ir.adicom.myapplication.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
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
    val _scope = viewModelScope

    init {
        val items = repository.getAll()
        notesList.addAll(items)
    }

    fun listItemOnClick(id: Int) = _scope.launch(Dispatchers.Main) {
        Timber.tag(TAG).d("listItemOnClick: $id")
        val route = Routes.ADD_NOTE + "/$id"
        _eventFlow.emit(HomeEvent.NavigateNext(route))
    }

    fun addNewNote() {
        Timber.tag(TAG).d("addNewNote")
    }

    fun saveNote(value: NoteModel) {
        Timber.tag(TAG).d("saveNote: $value")
        val isNoteEmpty = value.let {
            it.title.isEmpty() && it.description.isEmpty()
        }
        if (isNoteEmpty) return

        if (value.id == -1) {
            val newId = repository.insert(value)
            val newValue = value.copy(
                id = newId
            )
            notesList.add(newValue)
        } else {
            repository.update(value)
            val itemIndex = notesList.indexOfFirst { it.id == value.id }
            notesList[itemIndex] = value
        }


    }

    sealed class HomeEvent {
        data class NavigateNext(val route: String): HomeEvent()
    }
}
