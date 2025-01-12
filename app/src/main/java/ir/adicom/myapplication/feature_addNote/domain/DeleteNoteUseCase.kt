package ir.adicom.myapplication.feature_addNote.domain


import ir.adicom.myapplication.core.domain.repository.NotesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteNoteUseCase(
    private val repository: NotesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {


    suspend fun execute(id: Int) {

        withContext(ioDispatcher) {
            repository.delete(id)
        }
    }

}