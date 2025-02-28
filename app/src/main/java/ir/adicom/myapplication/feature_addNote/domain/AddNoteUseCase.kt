package ir.adicom.myapplication.feature_addNote.domain

import ir.adicom.myapplication.core.domain.models.NoteModel
import ir.adicom.myapplication.core.domain.repository.NotesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddNoteUseCase(
    private val repository: NotesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {


    suspend fun execute(noteModel: NoteModel) {

        withContext(ioDispatcher) {

            if (noteModel.id == -1) {
                repository.insert(noteModel)
            } else {
                repository.update(noteModel)
            }
        }
    }

}