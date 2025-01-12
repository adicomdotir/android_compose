package ir.adicom.myapplication.feature_addNote.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.adicom.myapplication.core.di.IODispatcher
import ir.adicom.myapplication.core.di.MainDispatcher
import ir.adicom.myapplication.core.domain.models.NoteModel
import ir.adicom.myapplication.feature_addNote.domain.AddNoteUseCase
import ir.adicom.myapplication.feature_addNote.domain.DeleteNoteUseCase
import ir.adicom.myapplication.feature_addNote.domain.GetNoteUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

const val TAG = ""

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getNoteUseCase: GetNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private var _noteId: Int = -1
    private var _title: MutableStateFlow<String> = MutableStateFlow("")
    val title = _title.asStateFlow()
    private var _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _event = MutableSharedFlow<AddNoteEvent>()
    val event = _event.asSharedFlow()

    private val _showConfirmationDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showConfirmationDialog = _showConfirmationDialog.asStateFlow()

    init {
        val noteId = savedStateHandle
            .get<Int>("id") ?: -1
        _noteId = noteId

        Timber.tag(TAG).d("init: noteId = $noteId")

        viewModelScope.launch(ioDispatcher) {
            if (noteId != -1) {
                val note = getNoteUseCase.execute(noteId) ?: return@launch
                _title.value = note.title
                _description.value = note.description
            }
        }
    }

    fun action(action: AddNoteAction) {
        when (action) {
            AddNoteAction.BackIconOnClick -> backIconOnClick()
            AddNoteAction.DeleteNote -> deleteNote()
            is AddNoteAction.DescriptionOnValueChange -> descriptionOnValueChange(action.value)
            AddNoteAction.HideConfirmationDialog -> hideConfirmationDialog()
            AddNoteAction.ShowConfirmationDialog -> showConfirmationDialog()
            is AddNoteAction.TitleOnValueChange -> titleOnValueChange(action.value)
        }
    }

    private fun titleOnValueChange(value: String) {
        Timber.tag(TAG).d("titleOnValueChange: title = ${title.value}")
        _title.value = value
    }

    private fun descriptionOnValueChange(value: String) {
        _description.value = value
    }

    private fun backIconOnClick() = viewModelScope.launch(ioDispatcher) {

        val noteModel = NoteModel(
            id = _noteId,
            title = _title.value,
            description = _description.value,
        )

        // Save Note
        addNoteUseCase.execute(noteModel)

        // Navigate Back
        viewModelScope.launch(mainDispatcher) {
            _event.emit(AddNoteEvent.NavigateBack)
        }

    }

    private fun hideConfirmationDialog() {
        _showConfirmationDialog.value = false
    }

    private fun showConfirmationDialog() {
        _showConfirmationDialog.value = true
    }

    private fun deleteNote() = viewModelScope.launch(ioDispatcher) {
        val itemId = _noteId
        deleteNoteUseCase.execute(itemId)

        hideConfirmationDialog()
        // Navigate Back
        viewModelScope.launch(mainDispatcher) {
            _event.emit(AddNoteEvent.NavigateBack)
        }
    }
}
