package ir.adicom.myapplication.addNote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.adicom.myapplication.models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddNoteViewModel : ViewModel() {
    private val _title = MutableStateFlow("")
    private val _description = MutableStateFlow("")
    val title = _title.asStateFlow()
    val description = _description.asStateFlow()
    private val _event = MutableSharedFlow<Event>()
    val event = _event.asSharedFlow()

    fun backIconOnClick() {
        val noteModel = NoteModel(
            id = -1,
            title = _title.value,
            description = _description.value
        )

        // SAVE

        // Navigate
        viewModelScope.launch(Dispatchers.Main) {
            _event.emit(Event.NavigateBack(noteModel))
        }
    }

    fun titleOnValueChange(value: String) {
        _title.value = value
    }

    fun descriptionOnValueChange(value: String) {
        _description.value = value
    }

    sealed class Event {
        data class NavigateBack(val noteModel: NoteModel) : Event()
    }
}