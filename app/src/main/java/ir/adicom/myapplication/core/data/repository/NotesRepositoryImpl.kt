package ir.adicom.myapplication.core.data.repository

import ir.adicom.myapplication.core.data.local.AppDatabase
import ir.adicom.myapplication.core.data.local.NoteEntity
import ir.adicom.myapplication.core.data.local.toModel
import ir.adicom.myapplication.core.domain.models.NoteModel
import ir.adicom.myapplication.core.domain.models.toEntity
import ir.adicom.myapplication.core.domain.repository.NotesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import timber.log.Timber

class NotesRepositoryImpl private constructor() : NotesRepository {
    private val _noteDao = AppDatabase.getInstance().noteDao()

    private val _newNoteInsertionListener = MutableSharedFlow<NoteModel>()
    override val newNoteInsertionListener: SharedFlow<NoteModel> =
        _newNoteInsertionListener.asSharedFlow()

    private val _updateNoteListener = MutableSharedFlow<NoteModel>()
    override val updateNoteListener: SharedFlow<NoteModel> =
        _updateNoteListener.asSharedFlow()

    private val _deleteNoteListener = MutableSharedFlow<Int>()
    override val deleteNoteListener: SharedFlow<Int> = _deleteNoteListener.asSharedFlow()

    companion object {
        private var _instance: NotesRepositoryImpl? = null

        fun getInstance(): NotesRepositoryImpl {
            if (_instance == null)
                _instance = NotesRepositoryImpl()

            return _instance as NotesRepositoryImpl
        }
    }

    override suspend fun getAll(): List<NoteEntity> {
        return _noteDao.getAll()
    }

    override suspend fun get(id: Int): NoteModel {
        return _noteDao.getItem(id).toModel()
    }

    override suspend fun insert(item: NoteModel): Int {
            Timber.tag("TAG").d(item.toString())
        try {
            val newId = _noteDao.insertItem(item.toEntity()).toInt()

            val newNote = item.copy(
                id = newId
            )
            _newNoteInsertionListener.emit(newNote)
            return newId
        } catch (e: Throwable) {
            Timber.tag("TAG").d(e.message)
        } finally {
            return -1
        }
    }

    override suspend fun update(item: NoteModel) {
        _noteDao.updateItem(item.toEntity())
        _updateNoteListener.emit(item)
    }

    override suspend fun delete(id: Int) {
        _noteDao.deleteItem(id)

        _deleteNoteListener.emit(id)
    }

}