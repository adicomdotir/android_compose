package ir.adicom.myapplication.core.domain.repository

import ir.adicom.myapplication.core.domain.models.NoteModel
import kotlinx.coroutines.flow.SharedFlow

interface NotesRepository {
    val newNoteInsertionListener: SharedFlow<NoteModel>
    val updateNoteListener: SharedFlow<NoteModel>
    val deleteNoteListener: SharedFlow<Int>

    suspend fun getAll(): List<NoteModel>

    suspend fun get(id: Int): NoteModel

    suspend fun insert(item: NoteModel): Int

    suspend fun update(item: NoteModel)

    suspend fun delete(id: Int)
}