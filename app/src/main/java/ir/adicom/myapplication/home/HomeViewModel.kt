package ir.adicom.myapplication.home

import androidx.lifecycle.ViewModel
import ir.adicom.myapplication.models.NoteModel
import ir.adicom.myapplication.models.dummyNotes
import timber.log.Timber

class HomeViewModel: ViewModel() {
    private val TAG = "HomeViewModel"

    private val _notes = ArrayList<NoteModel>(dummyNotes())
    val notes = _notes.toList()

    fun listItemOnClick(id: Int) {
        Timber.tag(TAG).d("listItemOnClick $id")
    }

    fun addNewNote() {
        Timber.tag(TAG).d("addNewNote")
    }
}