package ir.adicom.myapplication.feature_addNote.domain


import ir.adicom.myapplication.core.domain.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteNoteUseCase(
    private val repository: NotesRepository
) {


    suspend fun execute(id: Int) {

        withContext(Dispatchers.IO) {
            repository.delete(id)
        }
    }

}