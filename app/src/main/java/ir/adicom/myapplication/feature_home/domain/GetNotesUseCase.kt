package ir.adicom.myapplication.feature_home.domain

import ir.adicom.myapplication.core.domain.models.NoteModel
import ir.adicom.myapplication.core.domain.repository.NotesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetNotesUseCase(
    private val repository: NotesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun execute(): List<NoteModel> {
        return withContext(ioDispatcher) {
            repository.getAll()
        }
    }
}
