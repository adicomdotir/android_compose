package ir.adicom.myapplication.feature_addNote.domain

import ir.adicom.myapplication.core.domain.models.NoteModel
import ir.adicom.myapplication.core.domain.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetNoteUseCase(
    private val repository: NotesRepository
) {


    suspend fun execute(id: Int): NoteModel {
        return withContext(Dispatchers.IO) {
            repository.get(id)
        }
    }
}