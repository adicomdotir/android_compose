package ir.adicom.myapplication.core.data.repository

import ir.adicom.myapplication.core.data.local.NoteDao
import ir.adicom.myapplication.core.data.local.toModel
import ir.adicom.myapplication.core.domain.models.NoteModel
import ir.adicom.myapplication.core.domain.models.toEntity
import ir.adicom.myapplication.core.domain.repository.NotesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NotesRepositoryImpl(private val noteDao: NoteDao) : NotesRepository {
    private val _newNoteInsertionListener = MutableSharedFlow<NoteModel>()
    override val newNoteInsertionListener: SharedFlow<NoteModel> =
        _newNoteInsertionListener.asSharedFlow()

    private val _updateNoteListener = MutableSharedFlow<NoteModel>()
    override val updateNoteListener: SharedFlow<NoteModel> =
        _updateNoteListener.asSharedFlow()

    private val _deleteNoteListener = MutableSharedFlow<Int>()
    override val deleteNoteListener: SharedFlow<Int> = _deleteNoteListener.asSharedFlow()


    override suspend fun getAll(): List<NoteModel> {
        return noteDao.getAll().map { it.toModel() }
    }

    override suspend fun get(id: Int): NoteModel {
        return noteDao.getItem(id).toModel()
    }

    override suspend fun insert(item: NoteModel): Int {
        val newId = noteDao.insertItem(item.toEntity()).toInt()
        val newNote = item.copy(
            id = newId
        )
        _newNoteInsertionListener.emit(newNote)
        return newId

    }

    override suspend fun update(item: NoteModel) {
        noteDao.updateItem(item.toEntity())
        _updateNoteListener.emit(item)
    }

    override suspend fun delete(id: Int) {
        noteDao.deleteItem(id)
        _deleteNoteListener.emit(id)
    }

}