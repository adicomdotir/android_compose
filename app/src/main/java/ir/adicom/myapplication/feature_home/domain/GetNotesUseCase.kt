package ir.adicom.myapplication.feature_home.domain

import ir.adicom.myapplication.core.domain.models.NoteModel
import ir.adicom.myapplication.core.domain.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetNotesUseCase constructor(
    private val repository: NotesRepository
) {
    suspend fun execute(): List<NoteModel> {
        return withContext(Dispatchers.IO) {
            repository.getAll()
        }
    }
}
