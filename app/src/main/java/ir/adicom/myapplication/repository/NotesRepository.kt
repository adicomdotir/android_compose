package ir.adicom.myapplication.repository

import ir.adicom.myapplication.data.local.AppDatabase
import ir.adicom.myapplication.data.local.toModel
import ir.adicom.myapplication.models.NoteModel
import ir.adicom.myapplication.models.toEntity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NotesRepository private constructor() {
    private val _noteDao = AppDatabase.getInstance().noteDao()

    private val _newNoteInsertionListener = MutableSharedFlow<NoteModel>()
    val newNoteInsertionListener: SharedFlow<NoteModel> = _newNoteInsertionListener.asSharedFlow()

    private val _updateNoteListener = MutableSharedFlow<NoteModel>()
    val updateNoteListener: SharedFlow<NoteModel> =
        _updateNoteListener.asSharedFlow()

    private val _deleteNoteListener = MutableSharedFlow<Int>()
    val deleteNoteListener: SharedFlow<Int> = _deleteNoteListener.asSharedFlow()

    companion object {
        private var _instance: NotesRepository? = null

        fun getInstance(): NotesRepository {
            if (_instance == null)
                _instance = NotesRepository()

            return _instance as NotesRepository
        }
    }

    fun getAll(): List<NoteModel> {
        return _noteDao.getAll().map {
            it.toModel()
        }
    }

    fun get(id: Int): NoteModel {
        return _noteDao.getItem(id).toModel()
    }

    suspend fun insert(item: NoteModel): Int {
        val newId = _noteDao.insertItem(item.toEntity()).toInt()
        val newNote = item.copy(
            id = newId
        )
        _newNoteInsertionListener.emit(item)
        return newId
    }

    suspend fun update(item: NoteModel) {
        _noteDao.updateItem(item.toEntity())
        _updateNoteListener.emit(item)
    }

    suspend fun delete(id: Int) {
        _noteDao.deleteItem(id)

        _deleteNoteListener.emit(id)
    }

}