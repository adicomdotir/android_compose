package ir.adicom.myapplication.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import ir.adicom.myapplication.models.NoteModel
import ir.adicom.myapplication.models.dummyNotes
import timber.log.Timber

class HomeViewModel : ViewModel() {
    private val TAG = "HomeViewModel"

    val notes = mutableStateListOf<NoteModel>().apply {
        addAll(dummyNotes())
    }

    fun listItemOnClick(id: Int) {
        Timber.tag(TAG).d("listItemOnClick $id")
    }

    fun addNewNote() {
        Timber.tag(TAG).d("addNewNote")
    }

    fun saveNote(newNote: NoteModel) {
        notes.add(newNote)
    }
}